package com.gavinmogan;

import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.testing.MojoRule;
import org.codehaus.plexus.PlexusTestCase;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.junit.MockServerRule;
import org.mockserver.model.Header;

import java.io.File;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class CodacyCoverageReporterMojoTest {
    private File pom;
    private CodacyCoverageReporterMojo codacyCoverageReporterMojo;

    @Rule
    public MojoRule rule = new MojoRule();

    @Rule
    public MockServerRule mockServerRule = new MockServerRule(this);
    private MockServerClient mockServerClient;

    @Before
    public void setUp() throws Exception {
        pom = PlexusTestCase.getTestFile( "src/test/resources/unit/project-to-test" );
        assertNotNull( pom );
        assertTrue( pom.exists() );

        codacyCoverageReporterMojo = (CodacyCoverageReporterMojo) rule.lookupConfiguredMojo( pom, "coverage" );
        assertNotNull( codacyCoverageReporterMojo );
        codacyCoverageReporterMojo.setCodacyApiBaseUrl("http://localhost:" + mockServerRule.getPort());
        codacyCoverageReporterMojo.setCommit("FAKECOMMIT");
        codacyCoverageReporterMojo.setApiToken("FAKE_API_TOKEN");
        codacyCoverageReporterMojo.setProjectToken("FAKE_PROJECT_TOKEN");

    }

    @Test
    public void testJacoco() throws Exception {
        String body = "{\"language\":\"Java\",\"total\":51,\"fileReports\":[{\"filename\":\"com/saucelabs/ci/OperatingSystemDescription.java\",\"total\":100,\"coverage\":{\"12\":1,\"15\":1,\"11\":1,\"9\":1,\"33\":1,\"22\":1,\"26\":1,\"50\":1,\"37\":1,\"13\":1,\"16\":1,\"10\":1,\"48\":1,\"32\":1,\"17\":1,\"25\":1,\"14\":1,\"47\":1,\"31\":1,\"18\":1,\"30\":1,\"52\":1,\"28\":1}},{\"filename\":\"com/saucelabs/ci/Browser.java\",\"total\":46,\"coverage\":{\"113\":1,\"34\":1,\"147\":1,\"73\":1,\"62\":1,\"33\":1,\"163\":1,\"50\":1,\"68\":1,\"61\":1,\"46\":1,\"155\":1,\"35\":1,\"152\":1,\"159\":1,\"59\":1,\"144\":1,\"54\":1,\"65\":1,\"32\":1,\"31\":1,\"151\":1,\"42\":1,\"27\":1,\"70\":1,\"38\":1,\"167\":1,\"160\":1,\"171\":1,\"30\":1,\"143\":1,\"29\":1,\"28\":1}},{\"filename\":\"com/saucelabs/ci/JobInformation.java\",\"total\":81,\"coverage\":{\"205\":1,\"169\":1,\"153\":1,\"51\":1,\"124\":1,\"276\":1,\"170\":1,\"265\":1,\"88\":1,\"40\":1,\"269\":1,\"236\":1,\"33\":1,\"273\":1,\"280\":1,\"250\":1,\"123\":1,\"61\":1,\"206\":1,\"296\":1,\"79\":1,\"279\":1,\"152\":1,\"268\":1,\"274\":1,\"87\":1,\"196\":1,\"281\":1,\"270\":1,\"188\":1,\"43\":1,\"285\":1,\"297\":1,\"241\":1,\"289\":1,\"60\":1,\"133\":1,\"96\":1,\"69\":1,\"278\":1,\"245\":1,\"95\":1,\"264\":1,\"42\":1,\"246\":1,\"189\":1,\"266\":1,\"238\":1,\"282\":1,\"277\":1,\"187\":1,\"235\":1,\"125\":1,\"247\":1,\"154\":1,\"143\":1,\"97\":1,\"179\":1,\"114\":1,\"272\":1,\"41\":1,\"105\":1,\"204\":1,\"305\":1,\"172\":1,\"161\":1}},{\"filename\":\"com/saucelabs/ci/CacheTimeUtil.java\",\"total\":75,\"coverage\":{\"34\":1,\"37\":1,\"24\":1,\"25\":1,\"31\":1,\"20\":1}},{\"filename\":\"com/saucelabs/ci/SeleniumVersion.java\",\"total\":0,\"coverage\":{}},{\"filename\":\"com/saucelabs/ci/BrowserFactory.java\",\"total\":65,\"coverage\":{\"205\":1,\"45\":1,\"34\":1,\"67\":1,\"169\":1,\"120\":1,\"93\":1,\"158\":1,\"142\":1,\"147\":1,\"216\":1,\"164\":1,\"170\":1,\"175\":1,\"104\":1,\"201\":1,\"33\":1,\"197\":1,\"186\":1,\"212\":1,\"163\":1,\"134\":1,\"123\":1,\"166\":1,\"107\":1,\"46\":1,\"206\":1,\"177\":1,\"209\":1,\"94\":1,\"83\":1,\"35\":1,\"79\":1,\"152\":1,\"103\":1,\"112\":1,\"199\":1,\"159\":1,\"59\":1,\"87\":1,\"76\":1,\"138\":1,\"71\":1,\"202\":1,\"185\":1,\"106\":1,\"137\":1,\"49\":1,\"36\":1,\"140\":1,\"180\":1,\"221\":1,\"60\":1,\"208\":1,\"111\":1,\"102\":1,\"96\":1,\"69\":1,\"162\":1,\"191\":1,\"151\":1,\"95\":1,\"184\":1,\"64\":1,\"53\":1,\"203\":1,\"115\":1,\"195\":1,\"156\":1,\"149\":1,\"178\":1,\"27\":1,\"210\":1,\"81\":1,\"187\":1,\"171\":1,\"92\":1,\"101\":1,\"154\":1,\"220\":1,\"143\":1,\"97\":1,\"179\":1,\"218\":1,\"222\":1,\"105\":1,\"211\":1,\"150\":1,\"91\":1,\"52\":1,\"172\":1,\"200\":1,\"119\":1,\"136\":1}},{\"filename\":\"com/saucelabs/ci/SODSeleniumConfiguration.java\",\"total\":0,\"coverage\":{}},{\"filename\":\"com/saucelabs/ci/SeleniumBuilderManager.java\",\"total\":0,\"coverage\":{}},{\"filename\":\"com/saucelabs/ci/sauceconnect/TunnelInformation.java\",\"total\":87,\"coverage\":{\"45\":1,\"12\":1,\"33\":1,\"50\":1,\"37\":1,\"13\":1,\"46\":1,\"16\":1,\"21\":1,\"17\":1,\"38\":1,\"18\":1,\"30\":1,\"29\":1}},{\"filename\":\"com/saucelabs/ci/sauceconnect/SauceConnectFourManager.java\",\"total\":67,\"coverage\":{\"45\":1,\"193\":1,\"302\":1,\"335\":1,\"223\":1,\"89\":1,\"51\":1,\"73\":1,\"164\":1,\"78\":1,\"324\":1,\"362\":1,\"40\":1,\"320\":1,\"44\":1,\"197\":1,\"280\":1,\"287\":1,\"291\":1,\"262\":1,\"186\":1,\"56\":1,\"329\":1,\"55\":1,\"303\":1,\"244\":1,\"37\":1,\"242\":1,\"107\":1,\"99\":1,\"155\":1,\"314\":1,\"79\":1,\"350\":1,\"103\":1,\"72\":1,\"330\":1,\"87\":1,\"263\":1,\"340\":1,\"196\":1,\"48\":1,\"281\":1,\"76\":1,\"347\":1,\"259\":1,\"202\":1,\"248\":1,\"185\":1,\"49\":1,\"241\":1,\"36\":1,\"300\":1,\"337\":1,\"39\":1,\"289\":1,\"267\":1,\"290\":1,\"25\":1,\"111\":1,\"344\":1,\"191\":1,\"245\":1,\"190\":1,\"322\":1,\"315\":1,\"53\":1,\"203\":1,\"75\":1,\"115\":1,\"156\":1,\"293\":1,\"357\":1,\"260\":1,\"27\":1,\"70\":1,\"210\":1,\"38\":1,\"81\":1,\"271\":1,\"304\":1,\"187\":1,\"165\":1,\"92\":1,\"261\":1,\"220\":1,\"30\":1,\"331\":1,\"222\":1,\"207\":1,\"288\":1,\"29\":1,\"41\":1,\"240\":1,\"211\":1,\"204\":1,\"342\":1,\"74\":1,\"243\":1,\"305\":1,\"131\":1,\"28\":1,\"301\":1,\"119\":1,\"239\":1}},{\"filename\":\"com/saucelabs/ci/sauceconnect/AbstractSauceTunnelManager.java\",\"total\":65,\"coverage\":{\"619\":1,\"113\":1,\"381\":1,\"169\":1,\"444\":1,\"120\":1,\"335\":1,\"182\":1,\"147\":1,\"589\":1,\"608\":1,\"124\":1,\"411\":1,\"501\":1,\"519\":1,\"276\":1,\"164\":1,\"324\":1,\"332\":1,\"362\":1,\"552\":1,\"321\":1,\"580\":1,\"121\":1,\"415\":1,\"40\":1,\"593\":1,\"318\":1,\"378\":1,\"110\":1,\"284\":1,\"549\":1,\"385\":1,\"578\":1,\"273\":1,\"497\":1,\"280\":1,\"454\":1,\"585\":1,\"186\":1,\"227\":1,\"329\":1,\"55\":1,\"212\":1,\"512\":1,\"163\":1,\"123\":1,\"174\":1,\"410\":1,\"382\":1,\"166\":1,\"336\":1,\"328\":1,\"509\":1,\"46\":1,\"325\":1,\"437\":1,\"441\":1,\"209\":1,\"609\":1,\"379\":1,\"426\":1,\"103\":1,\"112\":1,\"387\":1,\"500\":1,\"330\":1,\"159\":1,\"631\":1,\"59\":1,\"550\":1,\"196\":1,\"375\":1,\"281\":1,\"572\":1,\"116\":1,\"498\":1,\"54\":1,\"511\":1,\"188\":1,\"148\":1,\"508\":1,\"213\":1,\"285\":1,\"185\":1,\"434\":1,\"360\":1,\"106\":1,\"431\":1,\"413\":1,\"386\":1,\"289\":1,\"582\":1,\"394\":1,\"290\":1,\"208\":1,\"122\":1,\"111\":1,\"102\":1,\"442\":1,\"191\":1,\"151\":1,\"322\":1,\"58\":1,\"439\":1,\"225\":1,\"53\":1,\"42\":1,\"528\":1,\"499\":1,\"293\":1,\"109\":1,\"189\":1,\"326\":1,\"210\":1,\"575\":1,\"564\":1,\"167\":1,\"277\":1,\"160\":1,\"187\":1,\"395\":1,\"165\":1,\"443\":1,\"573\":1,\"412\":1,\"520\":1,\"438\":1,\"319\":1,\"551\":1,\"194\":1,\"215\":1,\"30\":1,\"535\":1,\"331\":1,\"618\":1,\"432\":1,\"359\":1,\"607\":1,\"485\":1,\"384\":1,\"211\":1,\"150\":1,\"323\":1,\"226\":1,\"453\":1,\"172\":1,\"406\":1,\"380\":1,\"566\":1,\"440\":1,\"377\":1,\"334\":1,\"229\":1,\"119\":1,\"576\":1,\"632\":1}}]}";
        setupMockServer(body);

        codacyCoverageReporterMojo.setCoverageReportFile(new File(pom, "jacoco/jacoco.xml"));
        codacyCoverageReporterMojo.execute();
    }

    @Test
    public void testCobertura() throws Exception {
        String body = "{\"language\":\"Java\",\"total\":25,\"fileReports\":[{\"filename\":\"com/gavinmogan/CodacyCoverageReporterMojo.java\",\"total\":81,\"coverage\":{\"205\":2,\"113\":2,\"120\":2,\"153\":1,\"158\":2,\"182\":3,\"216\":2,\"124\":1,\"164\":4,\"135\":2,\"121\":1,\"88\":2,\"141\":2,\"139\":2,\"132\":2,\"201\":4,\"212\":2,\"224\":4,\"134\":2,\"123\":1,\"107\":2,\"217\":2,\"209\":2,\"233\":2,\"144\":4,\"228\":2,\"188\":2,\"148\":2,\"213\":2,\"185\":3,\"137\":2,\"126\":1,\"36\":2,\"140\":2,\"180\":3,\"208\":2,\"133\":2,\"122\":1,\"111\":2,\"102\":2,\"151\":1,\"225\":4,\"109\":2,\"149\":2,\"160\":1,\"187\":3,\"125\":1,\"194\":1,\"183\":3,\"129\":2,\"168\":1,\"232\":2,\"150\":1,\"204\":2,\"229\":2,\"200\":4,\"119\":2,\"161\":2}}]}";
        setupMockServer(body);

        codacyCoverageReporterMojo.setCoverageReportFile(new File(pom, "cobertura/coverage.xml"));
        codacyCoverageReporterMojo.execute();
    }

    @Test
    public void testCoberturaNotExistFailingOkay() throws Exception {
        String body = "{\"language\":\"Java\",\"total\":25,\"fileReports\":[{\"filename\":\"com/gavinmogan/CodacyCoverageReporterMojo.java\",\"total\":81,\"coverage\":{\"205\":2,\"113\":2,\"120\":2,\"153\":1,\"158\":2,\"182\":3,\"216\":2,\"124\":1,\"164\":4,\"135\":2,\"121\":1,\"88\":2,\"141\":2,\"139\":2,\"132\":2,\"201\":4,\"212\":2,\"224\":4,\"134\":2,\"123\":1,\"107\":2,\"217\":2,\"209\":2,\"233\":2,\"144\":4,\"228\":2,\"188\":2,\"148\":2,\"213\":2,\"185\":3,\"137\":2,\"126\":1,\"36\":2,\"140\":2,\"180\":3,\"208\":2,\"133\":2,\"122\":1,\"111\":2,\"102\":2,\"151\":1,\"225\":4,\"109\":2,\"149\":2,\"160\":1,\"187\":3,\"125\":1,\"194\":1,\"183\":3,\"129\":2,\"168\":1,\"232\":2,\"150\":1,\"204\":2,\"229\":2,\"200\":4,\"119\":2,\"161\":2}}]}";
        setupMockServer(body);

        codacyCoverageReporterMojo.setCoverageReportFile(new File(pom, "cobertura/coverage-not-exist.xml"));
        codacyCoverageReporterMojo.setFailOnMissingReportFile(false);
        codacyCoverageReporterMojo.execute();
    }

    @Test(expected=MojoFailureException.class)
    public void testCoberturaNotExistFailingNotOkay() throws Exception {
        String body = "{\"language\":\"Java\",\"total\":25,\"fileReports\":[{\"filename\":\"com/gavinmogan/CodacyCoverageReporterMojo.java\",\"total\":81,\"coverage\":{\"205\":2,\"113\":2,\"120\":2,\"153\":1,\"158\":2,\"182\":3,\"216\":2,\"124\":1,\"164\":4,\"135\":2,\"121\":1,\"88\":2,\"141\":2,\"139\":2,\"132\":2,\"201\":4,\"212\":2,\"224\":4,\"134\":2,\"123\":1,\"107\":2,\"217\":2,\"209\":2,\"233\":2,\"144\":4,\"228\":2,\"188\":2,\"148\":2,\"213\":2,\"185\":3,\"137\":2,\"126\":1,\"36\":2,\"140\":2,\"180\":3,\"208\":2,\"133\":2,\"122\":1,\"111\":2,\"102\":2,\"151\":1,\"225\":4,\"109\":2,\"149\":2,\"160\":1,\"187\":3,\"125\":1,\"194\":1,\"183\":3,\"129\":2,\"168\":1,\"232\":2,\"150\":1,\"204\":2,\"229\":2,\"200\":4,\"119\":2,\"161\":2}}]}";
        setupMockServer(body);

        codacyCoverageReporterMojo.setCoverageReportFile(new File(pom, "cobertura/coverage-not-exist.xml"));
        codacyCoverageReporterMojo.setFailOnMissingReportFile(true);
        codacyCoverageReporterMojo.execute();
    }

    private void setupMockServer(String body) {
        mockServerClient
                .when(
                        request()
                                .withMethod("POST")
                                .withPath("/2.0/coverage/FAKECOMMIT/java")
                                .withHeaders(
                                        new Header("content-type", "application/json"),
                                        new Header("api_token", "FAKE_API_TOKEN"),
                                        new Header("project_token", "FAKE_PROJECT_TOKEN")
                                )
                                .withBody(body)
                )
                .respond(
                        response()
                                .withStatusCode(200)
                );
    }
}
