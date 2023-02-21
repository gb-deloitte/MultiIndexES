package com.hashedin.broadcast.searchengine.esindex;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "certificate")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CertificateIndex {

    @Id
    int id;
    String name;
    int  expiry;


}
