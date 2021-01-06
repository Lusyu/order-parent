package contracts.controller.secret.SecretOrderController

import org.springframework.cloud.contract.spec.Contract


/**
 * 根据用户id获取demo
 */
Contract.make {
    request {
        method GET()
        url $(c(regex("/secret/demo/")), p("/secret/demo/"));
    }
    response {
        status OK()
        body(
                success: $(c(true), p(regex(anyBoolean()))),
                data: [[
                               id        : $(c("5ff3e1ab3017c813400bf90e"), p(regex("[0-9a-z]{24}"))),
                               name      : $(c("jack"), p(regex(".+"))),
                               price      : $(c("1000"), p(regex(".+"))),
                               createTime: $(c("2020-01-05"), p(regex(".+")))
                       ]]
        )
        headers {
            contentType(applicationJsonUtf8())
        }
    }
}
