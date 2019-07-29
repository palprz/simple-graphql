import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

public class SimpleGraphQL {

    public static void main(String[] args) throws IOException {
        // Get input data
        String schema = readFile("schema-graphql.txt");
        String query = readFile("query-graphql.txt");

        GraphQLSchema graphQLSchema = buildSchema(schema);

        // Run query
        GraphQL build = GraphQL.newGraphQL(graphQLSchema).build();
        ExecutionResult executionResult = build.execute(query);

        // Display results
        System.out.println(executionResult.getData().toString());
    }

    private static GraphQLSchema buildSchema(String schema) {
        RuntimeWiring runtimeWiring = buildWiring();
        return new SchemaGenerator().makeExecutableSchema(new SchemaParser().parse(schema), runtimeWiring);
    }

    private static RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query")
                        .dataFetcher("personById", Data.getPersonByIdDataFetcher()))
                .type(newTypeWiring("Query")
                        .dataFetcher("personByName", Data.getPersonByNameDataFetcher()))
                .type(newTypeWiring("Person")
                        .dataFetcher("country", Data.getCountryDataFetcher()))
                .build();
    }

    private static String readFile(String fileName) throws IOException {
        StringBuilder sb = new StringBuilder();
        Stream<String> lines = Files.lines(Paths.get(fileName));
        lines.forEach(line -> sb.append(line));
        lines.close();

        return sb.toString();
    }

}
