package contracts.controller.secret.SecretOrderController

import org.springframework.cloud.contract.spec.Contract;

/**
 * 根据指定id删除demo
 */
Contract.make {
    request {
        method DELETE()
        urlPath($(c(regex("/secret/demo/[0-9a-z]{24}/")), p("/secret/demo/5ff3e1ab3017c813400bf90e/")))
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
