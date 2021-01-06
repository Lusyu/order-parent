package contracts.controller.OrderController

import org.springframework.cloud.contract.spec.Contract

/**
 * 获取demo列表
 */
Contract.make {
    request {
        method GET()
        url $(c("/demo/"), p("/demo/"))
        headers {
            contentType(applicationJsonUtf8())
        }
    }
    response {
        status OK()
        body(
                success: $(c(true), p(regex(anyBoolean()))),
                data: [[
                               id        : $(c("5ff3e1ab3017c813400bf90e"), p(regex("[0-9a-z]{24}"))),
                               name      : $(c("jack"), p(regex(".+"))),
                               price      : $(c("1000"), p(regex(".+"))),
                               createTime: $(c("广州市..."), p(regex(".+"))),
                       ]]
        )
        headers {
            contentType(applicationJsonUtf8())
        }
    }
}
