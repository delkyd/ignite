/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ignite.internal.processors.igfs.client;

import org.apache.ignite.igfs.IgfsPath;
import org.apache.ignite.internal.processors.igfs.IgfsContext;
import org.apache.ignite.internal.util.typedef.internal.S;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

/**
 * IGFS client list paths callable.
 */
public class IgfsClientListPathsCallable extends IgfsClientAbstractCallable<Collection<IgfsPath>> {
    /** Type ID. */
    public static final short TYPE_ID = 7;

    /** */
    private static final long serialVersionUID = 0L;

    /**
     * Default constructor.
     */
    public IgfsClientListPathsCallable() {
        // NO-op.
    }

    /**
     * Constructor.
     *
     * @param igfsName IGFS name.
     * @param path Path.
     */
    public IgfsClientListPathsCallable(@Nullable String igfsName, IgfsPath path) {
        super(TYPE_ID, igfsName, path);
    }

    /** {@inheritDoc} */
    @Override public Collection<IgfsPath> call0(IgfsContext ctx) throws Exception {
        return ctx.igfs().listPaths(path);
    }

    /** {@inheritDoc} */
    @Override public String toString() {
        return S.toString(IgfsClientListPathsCallable.class, this);
    }
}
