package com.intuit.app.client;

import java.util.LinkedList;
import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.intuit.app.models.BaseNode;
import com.intuit.app.models.Label;
import com.intuit.app.models.NodeType;
import com.intuit.app.models.Timestamps;
import com.intuit.app.models.User;
import com.intuit.app.web.change.NodesChangeRequest;
import com.intuit.app.web.change.NodesChangeResponse;


public class CreateRootNode {

    private static final Logger log = LoggerFactory.getLogger(CreateNoteClient.class);

    public static void main(String args[]) {
        new CreateRootNode().addNewNote();
    }


    public void addNewNote() {

        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json");
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        NodesChangeRequest nodesChangeRequest = new NodesChangeRequest();
        nodesChangeRequest.setRequestId("xvSHS123zcipfjsTYVSLP323qklsbcNJDGYGD23asa");
        nodesChangeRequest.setNodes(createNewNodes());

        HttpEntity<NodesChangeRequest> request = new HttpEntity<NodesChangeRequest>(nodesChangeRequest, headers);
        NodesChangeResponse response = restTemplate.postForObject("http://localhost:8080/intuit/v1/notes/change/", request, NodesChangeResponse.class);
        log.info(response.getRequestId());

    }

    private List<BaseNode> createNewNodes() {

        List<BaseNode> nodes = new LinkedList<>();
        BaseNode.NodesBuilder nodesBuilder = new BaseNode.NodesBuilder();
        List<Label> labels = getLabels();
        Timestamps timestamps = getTimeStamps();

        String parentId = getDateTime()+"734854416";
        nodesBuilder.setNodeId(getDateTime()+"734854416")
                .setParentId("root")
                .setDeleted(false)
                .setTitle("Intuit App Title 1")
                .setArchived(false)
                .setBaseVersion(1)
                .setChecked(false)
                //.setLabels(labels)
                .setNodeType(NodeType.LIST)
                .setPinned(true)
                .setTrashed(false)
                .setTimestamps(timestamps)
                .setCreatedBy(new User("saurabhkakar05","saurabh.kakar05@gmail.com"))
                .setLastModifiedBy(new User("saurabhkakar05","saurabh.kakar05@gmail.com"));

        nodes.add(new BaseNode(nodesBuilder));



        return nodes;
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

    private List<Label> getLabels() {
        List<Label> labels = new LinkedList<>();

        Label one = new Label();
        one.setLabelName("Important");
        one.setLabelId(new Long(1));
        one.setSelected(true);

        Label two = new Label();
        two.setLabelName("Home");
        two.setLabelId(new Long(2));
        two.setSelected(true);

        labels.add(one);
        labels.add(two);
        return labels;
    }


}
