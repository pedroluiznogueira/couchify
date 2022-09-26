package com.github.pedroluiznogueira.couchify.repository;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.Scope;
import com.couchbase.client.java.json.JsonObject;
import com.couchbase.client.java.kv.GetResult;
import com.couchbase.client.java.kv.MutationResult;
import com.github.pedroluiznogueira.couchify.entity.Playlist;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class PlaylistRepository {
    private static final String COLLECTION_NAME = "playlist";

    private final Cluster cluster;
    private final Bucket bucket;
    private final Scope scope;
    private final Collection collection;

    public PlaylistRepository(final Cluster cluster,
                              final Bucket playlistBucket,
                              @Value("${couchbase.scope}") final String scopeName) {
        this.cluster = cluster;
        this.bucket = playlistBucket;
        this.scope = bucket.scope(scopeName);
        this.collection = scope.collection(COLLECTION_NAME);
    }

    public String findByIdAsString(final String id) {
        return Optional.ofNullable(collection.get(id))
                .map(GetResult::contentAsObject)
                .map(JsonObject::toString)
                .orElseThrow(() -> new IllegalArgumentException("Document not found for key: " + id));
    }

    public Playlist findByIdAsPlaylist(String id) {
        return Optional.ofNullable(collection.get(id))
                .map(result -> result.contentAs(Playlist.class))
                .orElseThrow(() -> new IllegalArgumentException("Document not found for key: " + id));
    }

    public long createPlaylist(final Playlist playlist) {
        playlist.setId(UUID.randomUUID().toString());
        return Optional.ofNullable(collection.insert(playlist.genKey(), playlist))
                .map(MutationResult::cas)
                .orElseThrow(() -> new IllegalArgumentException("Unable to create document"));
    }
}
