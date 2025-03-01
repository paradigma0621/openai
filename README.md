Demo project for SpringAI with OpenAI

Uses Java 21

Observation: requires to start Vector Database (ie: ChromaDB)
    docker run -it --rm --name chroma -p 8000:8000 ghcr.io/chroma-core/chroma:0.4.15

*IMPORTANT OBSERVATION*: For every new start of this microservice, stop and run again ChromaDB, otherwise, it will register multiple times the same documents.
