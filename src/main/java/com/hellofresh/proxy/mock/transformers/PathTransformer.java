package com.hellofresh.proxy.mock.transformers;

import com.github.tomakehurst.wiremock.extension.requestfilter.RequestFilterAction;
import com.github.tomakehurst.wiremock.extension.requestfilter.RequestWrapper;
import com.github.tomakehurst.wiremock.extension.requestfilter.StubRequestFilter;
import com.github.tomakehurst.wiremock.http.Request;

import static com.github.tomakehurst.wiremock.extension.requestfilter.RequestFilterAction.continueWith;

public class PathTransformer extends StubRequestFilter {

    @Override
    public RequestFilterAction filter(Request request) {
        String PATH = "/scm/order-planning-service";
        boolean useLocalEnv = request.getAbsoluteUrl().contains(PATH);
        Request wrappedRequest = RequestWrapper
                .create()
                .transformAbsoluteUrl(url -> url.replace(PATH, ""))
                .wrap(request);
        return continueWith(useLocalEnv ? wrappedRequest : request);
    }

    @Override
    public String getName() {
        return "path-transformer";
    }
}
