package contracts.controller.secret.SecretOrderController

import org.springframework.cloud.contract.spec.Contract

/**
 * 获取指定订单状态
 */
Contract.make {
    request {
        method GET()
        url $(c(regex("/secret/order/status/[0-9a-z]+/")),p("/secret/order/status/594784290949780165218436/"))
    }
    response {
        status OK()
        body(
                success: $(c(true),p(regex(anyBoolean()))),
                data:  $(c(1),p(regex(".+")))
        )
        headers {
            contentType(applicationJsonUtf8())
        }
    }

}
