package pl.piomin.services.department;

import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.specto.hoverfly.junit.core.Hoverfly;
import io.specto.hoverfly.junit5.HoverflyExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import pl.piomin.services.department.model.Department;
import pl.piomin.services.department.model.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.specto.hoverfly.junit.core.SimulationSource.dsl;
import static io.specto.hoverfly.junit.dsl.HoverflyDsl.service;
import static io.specto.hoverfly.junit.dsl.HttpBodyConverter.json;
import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(HoverflyExtension.class)
public class DepartmentAPIAdvancedTest {

    @Autowired
    KubernetesClient client;

    @BeforeAll
    static void setup(Hoverfly hoverfly) {
        System.setProperty(Config.KUBERNETES_TRUST_CERT_SYSTEM_PROPERTY, "true");
        System.setProperty(Config.KUBERNETES_AUTH_TRYKUBECONFIG_SYSTEM_PROPERTY, "false");
        System.setProperty(Config.KUBERNETES_AUTH_TRYSERVICEACCOUNT_SYSTEM_PROPERTY,
            "false");
        System.setProperty(Config.KUBERNETES_HTTP2_DISABLE, "true");
        System.setProperty(Config.KUBERNETES_NAMESPACE_SYSTEM_PROPERTY, "pchico83");
        hoverfly.simulate(dsl(service("kubernetes.pchico83.svc")
            .get("/api/v1/namespaces/pchico83/configmaps/department")
            .willReturn(success().body(json(buildConfigMap())))));
    }

    private static ConfigMap buildConfigMap() {
        return new ConfigMapBuilder().withNewMetadata()
            .withName("department").withNamespace("pchico83")
            .endMetadata()
            .addToData("application.properties",
                "spring.data.mongodb.uri=mongodb://localhost:27017/test")
            .build();
    }

    @Autowired
    TestRestTemplate restTemplate;

    private Service buildService() {
        return new ServiceBuilder().withNewMetadata().withName("employee")
                .withNamespace("pchico83").withLabels(new HashMap<>())
                .withAnnotations(new HashMap<>()).endMetadata().withNewSpec().addNewPort()
                .withPort(8080).endPort().endSpec().build();
    }

    private Endpoints buildEndpoints() {
        return new EndpointsBuilder().withNewMetadata()
            .withName("employee").withNamespace("pchico83")
            .endMetadata()
            .addNewSubset().addNewAddress()
            .withIp("employee.pchico83").endAddress().addNewPort().withName("http")
            .withPort(8080).endPort().endSubset()
            .build();
    }

    private List<Employee> buildEmployees() {
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee();
        employee.setId("abc123");
        employee.setAge(30);
        employee.setName("Test");
        employee.setPosition("test");
        employees.add(employee);
        return employees;
    }

    private String prepareUrl() {
        return client.getConfiguration().getMasterUrl()
            .replace("/", "")
            .replace("https:", "");
    }

}
