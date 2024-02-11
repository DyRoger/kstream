package com.dyroger.kstream.Processor;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dyroger.kstream.model.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import jakarta.annotation.PostConstruct;

@Component
public class EventStreamProcessor {

    @Autowired
    StreamsBuilder streamsBuilder;

    @PostConstruct
    public void streamTopology() {
        KStream<String, String> kStream = streamsBuilder.stream("input-topic",
                Consumed.with(Serdes.String(), Serdes.String()));
        // kStream
        //         .peek((key, value) -> System.out.println("Key :: " + key + "Value :: " + value))
        //         .to("output-topic", Produced.with(Serdes.String(), Serdes.String()));

        // Map the data to Employee objects using ObjectMapper
        KStream<String, String> modifiedStream = kStream.mapValues(value -> {
            // Convert the string to an Employee object using ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();
            Employee employee = new Employee();
            try {
                employee = objectMapper.readValue(value, Employee.class);
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            // Modify the Employee data as needed
            employee.modifyData();

            // Convert the modified Employee object back to a string
            try {
                return objectMapper.writeValueAsString(employee);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return null;
        });

        // Produce the modified data to another topic as strings
        modifiedStream.
        peek((k,v) -> System.out.println("Key :: "+k + "Value :: "+v.toString()))
        .to(
            "output-topic",
            Produced.with(Serdes.String(), Serdes.String())
        );


    }

}
