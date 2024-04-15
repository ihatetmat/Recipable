package capstone.recipable.domain.user.domain.oauth;

public interface OAuthHandler {
    OAuthTransactionResult retrieveOAuthDetail(OAuthProcessingData request);
}
