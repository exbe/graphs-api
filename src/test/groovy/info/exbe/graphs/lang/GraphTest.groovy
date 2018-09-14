package info.exbe.graphs.lang


import spock.lang.Specification

class GraphTest extends Specification {

    def graphType =  Graph.class.getDeclaredClasses()

    def "graph consists of nodes and edges"() {
        when:
            graphType

        then:
        graphType.contains(Graph.Node)
        graphType.contains(Graph.Edge)
    }

    def "node can exist by itself"() {
        when:
            def n = createNode("id-0")

        then:
            n.getID() == "id-0"
    }

    def "node with same id are equal"() {
        when:
           def n = createNode("1")
           def another = createNode("1")

        then:
           n.equalsByID(another)
    }

    def "node is not equal none-node"() {
        when:
        def n = createNode("1")

        then:
        !n.equalsByID("1")
    }

    def "edge can exist by itself"() {
        when:
            def e = createUndirectedEdge("id-0","id-1")

        then:
            e.getNode().getID() == "id-0"
            e.getOtherNode().getID() == "id-1"
    }


    def "edge is undirected by default"() {
        when:
        def e = createUndirectedEdge("id-0","id-1")

        then:
        !e.isOrdered()
    }

    def "directed edge"() {
        when:
        def e = createDirectedEdge("id-0","id-1")

        then:
        e.isOrdered()
    }

    def "graph defines visitable types"() {
        when:
         graphType

        then:
        graphType.contains(Graph.Iterable)
    }


    def "node can be visited"() {
        when:
        def types =  Graph.Node.class.getGenericInterfaces()

        then:
        types.contains(Graph.Visitable)
    }

    def "edge can be visited"() {
        when:
        def types =  Graph.Edge.class.getGenericInterfaces()

        then:
        types.contains(Graph.Visitable)
    }

    def "graph defines iterable types "() {
        when:
         graphType

        then:
        graphType.contains(Graph.Iterable)
    }

    def "graph visitable types must be iterated, therefore graph iterator is a visitable itself"() {
        when:
        def visitable =  Mock(Graph.Iterable)
        def visitor = Mock(Graph.Visitor)

        then:
        visitable.accept(visitor)
        noExceptionThrown()
    }



    private static  Graph.Node<String> createNode(String id) {
        new Graph.Node<String>() {
            @Override
            String getID() {
                return id
            }
        }
    }


    private static Graph.Edge<Graph.Node<String>, Graph.Node<String>> createDirectedEdge(String id, String anotherID) {
        new TestEdge(id, anotherID) {

            @Override
            boolean isOrdered() {
                return true
            }
        }
    }

    private static Graph.Edge<Graph.Node<String>, Graph.Node<String>> createUndirectedEdge(String id, String anotherID) {
        new TestEdge(id, anotherID)
    }

    private static class TestEdge implements Graph.Edge<Graph.Node<String>, Graph.Node<String>> {

        private String id
        private String anotherID

        TestEdge(String id, String anotherID) {
            this.id = id
            this.anotherID = anotherID
        }

        @Override
        Graph.Node<String> getNode() {
            return createNode(id)
        }

        @Override
        Graph.Node<String> getOtherNode() {
            return createNode(anotherID)
        }
    }


}
