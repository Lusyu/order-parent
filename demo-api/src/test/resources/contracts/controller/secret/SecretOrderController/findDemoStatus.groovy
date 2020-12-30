package contracts.controller.secret.SecretOrderController

import org.springframework.cloud.contract.spec.Contract

/**
 * 获取指定demo状态
 */
Contract.make {
    request {
        method GET()
        url $(c(regex("/secret/demo/status/[0-9a-z]+/")),p("/secret/demo/status/59bbb4290d49c8016ea18bb6/"))
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
