package contracts.controller.OrderController

import org.springframework.cloud.contract.spec.Contract

/**
 * 修改指定demo状态
 *
 */
Contract.make {
    request {
        method PATCH()
        url $(c("/demo/[0-9a-z]{24}/status/[0-6]{1}/")
                , p("/demo/59bbb4290d49c8016ea18bb6/status/1/"))
        headers {
            header('Authorization',
                    $(c(regex('Bearer [0-9a-z]{8}(-[0-9a-z]{4}){3}-[0-9a-z]{12}'))
                    , p('Bearer 4e57dbe7-6a58-401b-9017-037909799e8a')))
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
