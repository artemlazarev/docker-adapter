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

package com.artipie.docker.manifest;

import com.artipie.docker.Digest;
import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Manifest link reference.
 * <p>
 * Can be resolved by image tag or digest.
 * </p>
 * @since 1.0
 */
public final class ManifestRef implements RefPath {

    /**
     * Path parts.
     */
    private final List<String> parts;

    /**
     * Manifest link from a digest.
     * @param digest Digest
     */
    public ManifestRef(final Digest digest) {
        this(
            Arrays.asList("revisions", digest.alg(), digest.digest(), "link")
        );
    }

    /**
     * Manifest link from a tag.
     * @param tag Name
     */
    public ManifestRef(final String tag) {
        this(
            Arrays.asList("tags", tag, "current/link")
        );
    }

    /**
     * Primary constructor.
     * @param parts Path parts
     */
    private ManifestRef(final List<String> parts) {
        this.parts = Collections.unmodifiableList(parts);
    }

    @Override
    public URI path() {
        return URI.create(String.join("/", this.parts));
    }
}

