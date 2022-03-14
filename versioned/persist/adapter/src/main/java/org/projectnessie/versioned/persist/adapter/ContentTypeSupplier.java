/*
 * Copyright (C) 2022 Dremio
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.projectnessie.versioned.persist.adapter;

import com.google.protobuf.ByteString;

@FunctionalInterface
public interface ContentTypeSupplier {
  ContentType getContentType(ByteString type);

  default boolean isIcebergTable(ByteString type) {
    return ContentType.ICEBERG_TABLE == getContentType(type);
  }

  default boolean isIcebergView(ByteString type) {
    return ContentType.ICEBERG_VIEW == getContentType(type);
  }

  default boolean isDeltaLakeTable(ByteString type) {
    return ContentType.DELTA_LAKE_TABLE == getContentType(type);
  }
}
