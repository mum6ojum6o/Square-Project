package rules

import org.junit.rules.ExternalResource
import org.koin.core.context.KoinContextHandler
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.logger.PrintLogger
import org.koin.core.module.Module
import org.koin.dsl.module
import org.mockito.kotlin.mock


class KoinGraphRule(private val modules: List<Module> = emptyList()): ExternalResource() {
    private val mockList = mutableSetOf<Module>()
    init {
        initKoin()
    }

    public override fun before() {
        initKoin()
    }

    public override fun after() {
        stopKoin()
    }

    inline fun<reified T: Any>mockInGraph(): KoinGraphRule {
        val thing = mock<T> { }
        addModule(
            module{
                single { thing }
            }
        )
        return this
    }

    fun addModule(module: Module) {
        mockList.remove(module)
        mockList.add(module)
        if(koinStarted()){
            loadKoinModules(mockList.toList())
        }
    }

    fun initKoin() {
        if(!koinStarted()){
            startKoin {
                PrintLogger()
                modules(mockList.union(modules).toList())
            }
        }
    }

    fun stopKoin() {
        org.koin.core.context.stopKoin()
    }

    private fun koinStarted() = KoinContextHandler.getOrNull() != null
}