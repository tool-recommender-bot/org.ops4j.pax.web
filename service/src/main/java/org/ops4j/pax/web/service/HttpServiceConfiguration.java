/* Copyright 2007 Alin Dreghiciu.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ops4j.pax.web.service;

public interface HttpServiceConfiguration
{

    int getHttpPort();

    boolean isHttpEnabled();

    int getHttpSecurePort();

    boolean isHttpSecureEnabled();

    /**
     * Returns the path to the keystore.
     *
     * @return path to the keystore.
     */
    String getSslKeystore();

    /**
     * Returns the password for keystore integrity check.
     *
     * @return the password for keystore integrity check
     */
    String getSslPassword();

    /**
     * Returns the password for keystore.
     *
     * @return the password for keystore
     */
    String getSslKeyPassword();

}
