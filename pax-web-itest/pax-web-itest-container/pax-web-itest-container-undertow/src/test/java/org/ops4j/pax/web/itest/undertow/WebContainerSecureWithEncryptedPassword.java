/*
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
package org.ops4j.pax.web.itest.undertow;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.OptionUtils;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.web.itest.base.HttpTestClient;
import org.ops4j.pax.web.itest.base.VersionUtil;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;

import static org.ops4j.pax.exam.CoreOptions.systemProperty;

@RunWith(PaxExam.class)
public class WebContainerSecureWithEncryptedPassword extends ITestBase {


	private Bundle installWarBundle;

	@Configuration
	public static Option[] configure() {
		return OptionUtils.combine(
				configureUndertow(),
				systemProperty("org.osgi.service.http.secure.enabled").value(
						"true"),
				systemProperty("org.ops4j.pax.web.ssl.keystore").value("src/test/resources-binary/wss40rev.jks"),
				systemProperty("org.ops4j.pax.web.ssl.password").value(
						"ENC(zuhuJxhS0aQYddRG/BMa3msqohW7hp/i)"),
				systemProperty("org.ops4j.pax.web.ssl.keypassword").value(
						"ENC(zuhuJxhS0aQYddRG/BMa3msqohW7hp/i)"),
				systemProperty("org.ops4j.pax.web.ssl.key.alias").value(
                                                "wss40rev"),
				systemProperty("org.ops4j.pax.web.ssl.clientauthneeded").value(
						"required"),
				systemProperty("org.ops4j.pax.web.ssl.truststore").value("src/test/resources-binary/wss40rev.jks"),
				systemProperty("org.ops4j.pax.web.ssl.truststore.password").value(
				                "ENC(zuhuJxhS0aQYddRG/BMa3msqohW7hp/i)"),
				systemProperty("org.ops4j.pax.web.enc.enabled").value(
                                                "true"),
				systemProperty("org.ops4j.pax.web.enc.masterpassword").value(
                                                "masterpasswordfortest"));
				
		                
	}

	@Before
	public void setUp() throws Exception {
		initWebListener();
		final String bundlePath = "mvn:org.ops4j.pax.web.samples/helloworld-wc/"
				+ VersionUtil.getProjectVersion();
		installWarBundle = installAndStartBundle(bundlePath);
		waitForWebListener();
		waitForServer("https://127.0.0.1:8443");
		testClient = new HttpTestClient("", "", "src/test/resources-binary/wss40rev.jks", "security");
	}

	@After
	public void tearDown() throws BundleException {
		if (installWarBundle != null) {
			installWarBundle.stop();
			installWarBundle.uninstall();
		}
	}


	@Test
	public void testWebContextPath() throws Exception {
	    testClient.testWebPath("https://127.0.0.1:8443/helloworld/wc",
                "<h1>Hello World</h1>");
	}
}