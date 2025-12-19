// ResourceNotFoundException.java
package com.example.demo.exception;

public class ResourceNotFoundException extends RuntimeException {

    // message required by spec: "Not found"
    public ResourceNotFoundException() {
        super("Not found");
    }
}
