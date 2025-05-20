# Java HTTP Server - Multithreaded Web Server

## Overview

This project implements a simple multithreaded HTTP server in Java. It handles basic GET requests, serves static files (HTML, images, etc.), and supports virtual hosting based on the `Host:` header.

## Requirements

- Java Development Kit (JDK)
- Linux or equivalent command-line environment
- Terminal tools: `javac`, `java`
- Basic knowledge of threads and I/O streams in Java

## Files

- `HttpServer.java`  
  - Main server class. Listens on a specified port and spawns a new `HttpServerSession` thread for each connection.
- `HttpServerSession.java`  
  - Extends `Thread`. Reads HTTP requests, delegates parsing to `HttpServerRequest`, and sends responses.
- `HttpServerRequest.java`  
  - Parses the HTTP GET request, extracts method, URI, protocol version, and headers (including `Host:`). Provides filename and host directory.
- Any static content directories matching hostnames (e.g., `localhost:50000/`, `example.com:50000/`) containing `index.html`, images, etc.

## Compilation

```bash
javac HttpServer.java HttpServerSession.java HttpServerRequest.java
```

## Usage

1. **Start the server**  
   ```bash
   java HttpServer <port>
   ```  
   - `<port>`: any unused port between 50000 and 65535.  
   - Example:  
     ```bash
     java HttpServer 50000
     Listening for connections on port 50000
     ```

2. **Access via browser or wget**  
   - **Browser**: Navigate to `http://<hostname>:<port>/<path>`  
     Example:  
     ```
     http://localhost:50000/index.html
     ```
   - **wget**:  
     ```bash
     wget http://<hostname>:<port>/<file>
     ```
     Use `md5sum` to verify binary files.

## Notes

- Only the GET method is supported.
- Returns `200 OK` with file contents or `404 File Not Found` with a simple message.
- Uses `BufferedOutputStream` for compliant CR/LF line endings and binary safety.
- Supports virtual hosting: files are served from a directory named `<Host>:<port>`.
- Handle exceptions gracefully and log requests and errors to the console.
- The server is multithreaded to allow concurrent clients; remove any artificial `sleep` before final testing.

