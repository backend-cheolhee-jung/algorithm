import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

interface Timer {
    fun <T> loggingTime(block: () -> T): T
}

class TimerImpl : Timer {
    override fun <T> loggingTime(block: () -> T): T {
        val start = System.currentTimeMillis()
        println("시작 시간: $start")
        val result = block.invoke()
        val end = System.currentTimeMillis()
        println("종료 시간: $end")
        println("--------------------")
        println("소요 시간: ${end - start}ms")
        return result
    }
}

internal class InvocationHandlerImpl(
    private val target: Any,
) : InvocationHandler {
    override operator fun invoke(
        proxy: Any?,
        method: Method,
        args: Array<Any>,
    ): Any? {
        println("메서드 실행 전: " + method.name)
        val result = method.invoke(target, *args)
        println("메서드 실행 후: " + method.name)
        return result
    }
}

fun main() {
    val timer = TimerImpl()
    val timerProxy = Proxy.newProxyInstance(
        Timer::class.java.classLoader,
        arrayOf(Timer::class.java),
        InvocationHandlerImpl(timer),
    ) as Timer

    timerProxy.loggingTime {
        Thread.sleep(1000)
    }
}