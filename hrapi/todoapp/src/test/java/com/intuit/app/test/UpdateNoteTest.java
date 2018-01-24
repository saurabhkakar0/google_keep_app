package com.intuit.app.test;

import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.app.ApplicationSpringBootInitializer;
import com.intuit.app.models.BaseNode;
import com.intuit.app.models.NodeType;
import com.intuit.app.models.Timestamps;
import com.intuit.app.models.User;
import com.intuit.app.service.notes.NotesService;
import com.intuit.app.web.change.NodesChangeRequest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationSpringBootInitializer.class)
@WebAppConfiguration
public class UpdateNoteTest {

    @MockBean
    NotesService notesService;


    private MockMvc mockMvc;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void updateNode() throws Exception {
        BaseNode.NodesBuilder nodesBuilder = new BaseNode.NodesBuilder();
        Timestamps timestamps = getTimeStamps();
        List<BaseNode> nodes = new LinkedList<>();

        nodesBuilder.setNodeId(getDateTime() + "734854416root")
                .setParentId("root")
                .setDeleted(false)
                .setTitle("Intuit App Title 1")
                .setArchived(false)
                .setBaseVersion(1)
                .setChecked(false)
                .setNodeType(NodeType.LIST)
                .setPinned(true)
                .setTrashed(false)
                .setCreatedBy(new User("saurabhkakar05", "saurabh.kakar05@gmail.com"))
                .setLastModifiedBy(new User("saurabhkakar05", "saurabh.kakar05@gmail.com"));

        nodes.add(new BaseNode(nodesBuilder));
        NodesChangeRequest nodesChangeRequest = new NodesChangeRequest();
        nodesChangeRequest.setNodes(nodes);


        Mockito.when(notesService.updateNodes(Mockito.anyObject())).thenReturn(nodes);

        final ResultActions resultActions = this.mockMvc.perform(post("/v1/notes/change/")
                .contentType(contentType)
                .content(asJsonString(nodesChangeRequest)))
                .andExpect(status().isOk());

    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            System.out.println(jsonContent);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String getDateTime() {
        return Long.toString(System.nanoTime());
    }

    private Timestamps getTimeStamps() {
        Timestamps timestamps = new Timestamps();
        System.out.println(new DateTime());
        timestamps.setCreated(new DateTime());
        timestamps.setUpdated(new DateTime());
        return timestamps;
    }
}