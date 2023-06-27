package com.freshtuna.sharp.config.resolver

import com.freshtuna.sharp.config.const.Const
import com.freshtuna.sharp.id.SharpID
import com.freshtuna.sharp.id.SharpIDInjection
import io.github.oshai.KotlinLogging
import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class SharpIDResolver : HandlerMethodArgumentResolver {

    val log = KotlinLogging.logger {  }

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(SharpIDInjection::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {

        val request: HttpServletRequest = webRequest.getNativeRequest(HttpServletRequest::class.java)
            ?: throw IllegalStateException("HttpServletRequest not found")

        return request.getAttribute(Const.SHARP_ID_HEADER_KEY) as? SharpID
            ?: throw IllegalArgumentException("사용자 정보가 존재하지 않습니다.")
    }
}
