package contracts

import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.spec.internal.HttpMethods

Contract.make {
    request {
        urlPath "/fetch-recommendations"
        method HttpMethods.PUT
        headers {
            header(contentType(), applicationJson())
        }
        body([
                userId         : anyUuid(),
                preferredGenres: ['Platformer', 'Stealth', 'Adventure']
        ])
    }
    response {
        status 200
        headers {
            header(contentType(), applicationJson())
        }
        body(
                [
                        'videogames' : [
                                [
                                        name : 'Red Dead Redemption',
                                        genre: 'Adventure'
                                ],
                                [
                                        name : 'Crash Bandicoot 3',
                                        genre: 'Platformer'
                                ]]
                ]
        )
    }
    priority(2)
}