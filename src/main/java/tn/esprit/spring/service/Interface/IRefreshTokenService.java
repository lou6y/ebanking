package tn.esprit.spring.service.Interface;

import java.util.Optional;

import tn.esprit.spring.entity.RefreshToken;

public interface IRefreshTokenService {
	public Optional<RefreshToken> findByToken(String token);
	public RefreshToken createRefreshToken(Long userId);
	public RefreshToken verifyExpiration(RefreshToken token);
	public int deleteByUserId(Long userId);

}
