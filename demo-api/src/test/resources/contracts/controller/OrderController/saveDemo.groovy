package contracts.controller.OrderController

import org.springframework.cloud.contract.spec.Contract;

/**
 * 新增新的demo
 */
Contract.make {
    request {
        method POST();
        url($(c("/demo/"), p("/demo/")))
        body(
                name: $(c(regex(".+")), p("jack")),
                price: $(c(regex(".+")), p("1000"))
        )
        headers {
            header("Authorization",
                    $(c("Bearer [0-9a-z]{8}(-[0-9a-z]{4}){3}-[0-9a-z]{12}"),
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
