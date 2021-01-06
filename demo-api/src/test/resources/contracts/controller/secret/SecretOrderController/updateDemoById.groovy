package contracts.controller.secret.SecretOrderController

import org.springframework.cloud.contract.spec.Contract

/**
 *根据id更新demo的名称和价格
 */
Contract.make{
    request {
        method PATCH()
        url $(c(regex("/secret/demo/id/[0-9a-z]{24}/name/.+/price/.+/"))
                ,p("/secret/demo/id/5ff3e1ab3017c813400bf90e/name/Lucy/price/999/"))
        headers {
            contentType(applicationJsonUtf8())
        }
    }
    response {
        status OK()
        body(
                success:$(c(true),p(regex(anyBoolean())))
        )
    }
}
