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

package com.artipie.docker;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link Digest}.
 *
 * @since 0.1
 */
public final class DigestTest {
    @Test
    void parsesValidString() {
        final Digest.FromString dgst = new Digest.FromString("sha256:1234");
        MatcherAssert.assertThat(dgst.valid(), new IsEqual<>(true));
        MatcherAssert.assertThat("bad algorithm", dgst.alg(), Matchers.is("sha256"));
        MatcherAssert.assertThat("bad digest", dgst.hex(), Matchers.is("1234"));
    }

    @Test
    void failsOnInvalidString() {
        final Digest.FromString dgst = new Digest.FromString("asd");
        MatcherAssert.assertThat(dgst.valid(), new IsEqual<>(false));
        Assertions.assertThrows(
            IllegalStateException.class, () -> dgst.alg(), "alg() didn't fail"
        );
        Assertions.assertThrows(
            IllegalStateException.class, () -> dgst.hex(), "digest() didn't fail"
        );
    }

    @Test
    void shouldHaveExpectedStringRepresentation() {
        final Digest.Sha256 digest = new Digest.Sha256(
            "6c3c624b58dbbcd3c0dd82b4c53f04194d1247c6eebdaab7c610cf7d66709b3b"
        );
        MatcherAssert.assertThat(
            digest.string(),
            new IsEqual<>("sha256:6c3c624b58dbbcd3c0dd82b4c53f04194d1247c6eebdaab7c610cf7d66709b3b")
        );
    }
}
