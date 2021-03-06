/*
 * MIT License
 *
 * Copyright (c) 2020 Artipie
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.artipie.docker.http;

import com.artipie.http.rq.RequestLine;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link UploadEntity.Request}.
 *
 * @since 0.2
 * @checkstyle ClassDataAbstractionCouplingCheck (2 lines)
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
class UploadEntityRequestTest {

    @Test
    void shouldReadName() {
        final UploadEntity.Request request = new UploadEntity.Request(
            new RequestLine("POST", "/v2/my-repo/blobs/uploads/", "HTTP/1.1").toString()
        );
        MatcherAssert.assertThat(request.name().value(), new IsEqual<>("my-repo"));
    }

    @Test
    void shouldReadUuid() {
        final UploadEntity.Request request = new UploadEntity.Request(
            new RequestLine("PATCH", "/v2/my-repo/blobs/uploads/123-abc", "HTTP/1.1").toString()
        );
        MatcherAssert.assertThat(request.uuid(), new IsEqual<>("123-abc"));
    }

    @Test
    void shouldReadDigest() {
        final UploadEntity.Request request = new UploadEntity.Request(
            new RequestLine(
                "PUT",
                "/v2/my-repo/blobs/uploads/123-abc?digest=sha256:12345",
                "HTTP/1.1"
            ).toString()
        );
        MatcherAssert.assertThat(request.digest().string(), new IsEqual<>("sha256:12345"));
    }
}
