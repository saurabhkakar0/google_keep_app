# intuitapp

Lets start with few important terms:

Node : It can be of following types :
        a. Note : It can have title, color, text(List Item) and images (Blob).
        b. List : It can have title, color, itemised text(List Item)  and images (Blob). In this we can have List Items that can be checked.
        c. List Items : These are simple text nodes that can be associated with a check box. List Items are associated with the parent
                        node. Parent Node can be of type Note and List. If it is of type List, then these List Item node can be checked.
        d. Blob : These nodes are the images attached to the node of type Note or List.                
                        
Label : You can assign a Label to the Node. These are independent entities and can exist on its own. There is many to many relationship between 
        Node and Label.
        Note : Only nodes of type Note and List can have Labels as these are the parent nodes.
                

1 For each request sent by front end client to server, following assumptions are made

    a. For each node in nodes list, nodeId must be present.
       nodeId will be UNIX based timestamp + uniqueId which needs to be send by front end client to the back end server.
       If nodeId is not present, then NodeIdNotPresentException will be thrown and Transaction will be rolled back.

    b. Client of this backend service is responsible for maintaining the state of all the nodes. On any change in the node on the
       client side, back end service will be called with the changed nodes.

    c. If node is root, then its parentId will be string "root". Else parentId will be the nodeId of the parent.
       For example If node "y" is of type LIST_ITEM or BLOB, then this node "y" must has parentId present in the request.
       Also parentId must be valid nodeId of node entity.

    d. All the timestamps related to the node will be send by front end. Following time stamps are required:
        a. created
        b. updated
        c. deleted
        d. trashed
        if isTrashed is true, then trashed timestamp must be valid.

    e. LIST_ITEM node will not be trashed, they can only be deleted. If deleted is true in the request, then back end server will
       delete this LIST_ITEM node from the database.

    f. if isChecked is valid for node type LIST_ITEM only. If it is true, then column isChecked in LIST_ITEM table will be set to true.

    g. You can only upload images of type jpeg and png only. Image size must be less than 2 MB. All the image BLOB will be stored in the ATTACHMENT
       table.

    h. If parent nodeId is set to isTrashed true, then all the child nodeIds (LIST_ITEM and BLOB) will also be set as isTrashed true.

    i. If parent nodeId is set to deleted true, then all the child nodeIds (LIST_ITEM and BLOB) will also be deleted too.

    j. Client is responsible for sending the following information regarding attached Labels to the Nodes :
        a. deleted - This will be set to true if the Label is removed by the user for the node it was assigned.
        b. selected - This will be set to true if the Label is selected first time by the user for the node.
        labelId must exist in the database for both the above operation.



2. For DDL scripts, Please refer to DDLs.txt in entitymodel folder.

3. Tools and Technologies Used:

====

     a.      Maven
     b.      Spring Boot
     c.      Spring JDBC
     d.      REST based microservice
     e.      MySql Database
     f.      SQL
     g.      JSON
     h.      Tomcat 8 Server
     i.      IntelliJ IDE
     j.      MySql Workbench
     k.      PostMan for testing

     

4. Following are the exposed APIs :

    a. http://localhost:8080/intuit/v1/notes/change/
       Above API can be used for :

         i. Create New Note : As mentioned in (1.a), API expects the NodeChangeRequest to have a nodeId present.
        ii. Update Note :
       iii. Trash Note : It has to be a NodeType NOTE or LIST.
        iv. Add Label to Note :
         v. Delete Label to Note :
        vi. Delete Note and all its associated items : It has to be a NodeType NOTE or LIST.
       vii. Delete LIST_ITEM node : This will hard delete the node.
       
    b. http://localhost:8080/intuit/v1/notes/list/ : To fetch all the Notes (active and trashed) for the user.
                                                     (Not Implemented yet)
    
    c. http://localhost:8080/intuit/v1/label/change/ : To insert, update or delete the Label.
                                                      (Not Implemented yet)

