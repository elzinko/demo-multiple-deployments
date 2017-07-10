package com.edelia.cd;

import static io.fabric8.kubernetes.assertions.Assertions.assertThat;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.fabric8.kubernetes.client.KubernetesClient;

@RunWith(Arquillian.class)
public class KubernetesIntegrationKT {

    @ArquillianResource
    KubernetesClient client;

    @Test
    public void testRunningPodStaysUp() throws Exception {
        assertThat(client).deployments().pods().isPodReadyForPeriod();
    }
}