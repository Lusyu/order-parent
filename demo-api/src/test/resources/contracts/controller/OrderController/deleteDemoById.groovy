package contracts.controller.OrderController

import org.springframework.cloud.contract.spec.Contract;

/**
 * 根据demo的id删除指定demo
 */
Contract.make {
    request {
        method DELETE()
        url ($(c(regex("/demo/[0-9a-z]{24}/")), p("/demo/5ff3e1ab3017c813400bf90e/")))
        headers {
            header("Authorization",
                    $(c(regex("Bearer [0-9a-z]{8}(-[0-9a-z]{4}){3}-[0-9a-z]{12}")),
                            p("Bearer 7879755a-a90f-11e9-8547-0f8730a4fe76")))
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
