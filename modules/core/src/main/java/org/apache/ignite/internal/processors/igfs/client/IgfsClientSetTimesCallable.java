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

import org.apache.ignite.binary.BinaryObjectException;
import org.apache.ignite.binary.BinaryRawReader;
import org.apache.ignite.binary.BinaryRawWriter;
import org.apache.ignite.igfs.IgfsPath;
import org.apache.ignite.internal.processors.igfs.IgfsContext;
import org.apache.ignite.internal.util.typedef.internal.S;
import org.apache.ignite.plugin.extensions.communication.MessageReader;
import org.apache.ignite.plugin.extensions.communication.MessageWriter;
import org.jetbrains.annotations.Nullable;

/**
 * IGFS client set times callable.
 */
public class IgfsClientSetTimesCallable extends IgfsClientAbstractCallable<Void> {
    /** Type ID. */
    public static final short TYPE_ID = 10;

    /** */
    private static final long serialVersionUID = 0L;

    /** Access time. */
    private long accessTime;

    /** Modification time. */
    private long modificationTime;

    /**
     * Default constructor.
     */
    public IgfsClientSetTimesCallable() {
        // NO-op.
    }

    /**
     * Constructor.
     *
     * @param igfsName IGFS name.
     * @param path Path.
     * @param accessTime Access time.
     * @param modificationTime Modification time.
     */
    public IgfsClientSetTimesCallable(@Nullable String igfsName, IgfsPath path, long accessTime,
        long modificationTime) {
        super(TYPE_ID, igfsName, path);

        this.accessTime = accessTime;
        this.modificationTime = modificationTime;
    }

    /** {@inheritDoc} */
    @Override public Void call0(IgfsContext ctx) throws Exception {
        ctx.igfs().setTimes(path, accessTime, modificationTime);

        return null;
    }

    /** {@inheritDoc} */
    @Override public void writeBinary0(BinaryRawWriter writer) throws BinaryObjectException {
        writer.writeLong(accessTime);
        writer.writeLong(modificationTime);
    }

    /** {@inheritDoc} */
    @Override public void readBinary0(BinaryRawReader reader) throws BinaryObjectException {
        accessTime = reader.readLong();
        modificationTime = reader.readLong();
    }

    /** {@inheritDoc} */
    @Override protected byte fieldsCount0() {
        return 2;
    }

    /** {@inheritDoc} */
    @Override protected boolean writeTo0(MessageWriter writer, int fieldId) {
        switch (fieldId) {
            case 0:
                return writer.writeLong("accessTime", accessTime);

            default:
                assert fieldId == 1;

                return writer.writeLong("modificationTime", modificationTime);
        }
    }

    /** {@inheritDoc} */
    @Override protected void readFrom0(MessageReader reader, int fieldId) {
        switch (fieldId) {
            case 0:
                accessTime = reader.readLong("accessTime");

            default:
                assert fieldId == 1;

                modificationTime = reader.readLong("modificationTime");
        }
    }

    /** {@inheritDoc} */
    @Override public String toString() {
        return S.toString(IgfsClientSetTimesCallable.class, this);
    }
}
