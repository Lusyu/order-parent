package contracts.controller.secret.SecretOrderController

import org.springframework.cloud.contract.spec.Contract

/**
 * 简单新增demo
 */
Contract.make {
    request {
        method POST()
        url $(c("/secret/demo/"), p("/secret/demo/"))
        body(
                name: $(c(regex(".+")), p("jack")),
                price: $(c(regex(".+")), p("1000")),
        )
        headers {
            contentType(applicationJsonUtf8())
        }
    }
    response {
        status OK()
        body(
                success: $(c(true), p(regex(anyBoolean())))
        )
        headers {
            contentType(applicationJsonUtf8())
        }
    }
}
