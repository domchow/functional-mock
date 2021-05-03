package com.hellofresh.proxy.mock.transformers;

import com.github.tomakehurst.wiremock.extension.requestfilter.RequestFilterAction;
import com.github.tomakehurst.wiremock.extension.requestfilter.RequestWrapper;
import com.github.tomakehurst.wiremock.extension.requestfilter.StubRequestFilter;
import com.github.tomakehurst.wiremock.http.Request;

import static com.github.tomakehurst.wiremock.extension.requestfilter.RequestFilterAction.continueWith;

public class PathTransformer extends StubRequestFilter {

    @Override
    public RequestFilterAction filter(Request request) {
        String PATH = "/demand";
        Request wrappedRequest = RequestWrapper
                .create()
                .transformAbsoluteUrl(url -> url.replace("/scm", ""))
                .wrap(request);
        return continueWith(request.getAbsoluteUrl().contains(PATH) ?
                wrappedRequest :
                request);
    }

    @Override
    public String getName() {
        return "path-transformer";
    }
}
