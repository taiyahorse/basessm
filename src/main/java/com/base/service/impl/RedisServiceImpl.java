package com.base.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.base.service.RedisService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;
import static java.lang.Boolean.TRUE;
import static org.slf4j.LoggerFactory.getLogger;

@Service("redisService")
public class RedisServiceImpl implements RedisService {
    private static final Logger LOGGER = getLogger(RedisServiceImpl.class);

    private static final String DEFAULT_CHARSET = "UTF-8";

    private final RedisTemplate<Serializable, Serializable> redisTemplate;

    /**
     * RedisServiceImpl
     *
     * @param redisTemplate redisTemplate
     */
    @Autowired
    public RedisServiceImpl(RedisTemplate<Serializable, Serializable> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void set(final byte[] key, final byte[] obj) throws DataAccessException {
        redisTemplate.execute((RedisCallback<Long>) connection -> {
            connection.set(key, obj);
            return 1L;
        });
    }

    @Override
    public <T> void set(final byte[] key, T obj) throws DataAccessException {
        final byte[] bValue = serialize(obj);
        redisTemplate.execute((RedisCallback<Long>) connection -> {
            connection.set(key, bValue);
            return 1L;
        });
    }

    @Override
    public <T> void set(String key, T obj) throws DataAccessException {
        final byte[] bKey = serialize(key);
        final byte[] bValue = serialize(obj);
        redisTemplate.execute((RedisCallback<Long>) connection -> {
            connection.set(bKey, bValue);
            return 1L;
        });
    }

    @Override
    public <T> Boolean setNX(String key, T obj) throws DataAccessException {
        final byte[] bKey = serialize(key);
        final byte[] bValue = serialize(obj);
        return redisTemplate.execute((RedisCallback<Boolean>) connection -> connection.setNX(bKey, bValue));
    }

    @Override
    public <T> void setEx(final byte[] key, T obj, final Long expireSeconds)
            throws DataAccessException {
        final byte[] bValue = serialize(obj);
        redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            connection.setEx(key, expireSeconds, bValue);
            return TRUE;
        });
    }

    @Override
    public <T> void setEx(String key, T obj, final Long expireSeconds)
            throws DataAccessException {
        final byte[] bKey = serialize(key);
        final byte[] bValue = serialize(obj);
        redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            connection.setEx(bKey, expireSeconds, bValue);
            return TRUE;
        });
    }

    @Override
    public <T> T get(final byte[] key) throws DataAccessException {
        final byte[] result = redisTemplate.
                execute((RedisCallback<byte[]>) connection -> connection.get(key));
        if (null == result || result.length == 0) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("????????????Key:{}", new String(key, Charset.forName(DEFAULT_CHARSET)));
            }
            return null;
        }
        return deserialize(result);
    }

    @Override
    public <T> T get(String key) throws DataAccessException {
        final byte[] bKey = serialize(key);
        final byte[] result = redisTemplate.
                execute((RedisCallback<byte[]>) connection -> connection.get(bKey));
        if (null == result || result.length == 0) {
            return null;
        }
        return deserialize(result);
    }

    @Override
    public Long del(final byte[] key) throws DataAccessException {
        if (null == key || key.length == 0) {
            return 0L;
        }
        return redisTemplate.execute((RedisCallback<Long>) connection -> {
            LOGGER.info("??????Redis?????????Key:{}", new String(key, Charset.forName(DEFAULT_CHARSET)));
            return connection.del(key);
        });
    }

    @Override
    public Set<byte[]> keys(final String key) throws DataAccessException {
        if (StringUtils.isEmpty(key)) {
            return new HashSet<>(0);
        }
        return redisTemplate.execute((RedisCallback<Set<byte[]>>) connection -> connection.keys(key.getBytes(Charset.forName(DEFAULT_CHARSET))));
    }

    @Override
    public Boolean exists(final String key) throws DataAccessException {
        return (Boolean) redisTemplate.execute((RedisCallback) connection -> connection.exists(serialize(key)));
    }

    @Override
    public Boolean flushDB() throws DataAccessException {
        return redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            connection.flushDb();
            LOGGER.info("??????Redis?????????.");
            return TRUE;
        });
    }

    @Override
    public Long dbSize() throws DataAccessException {
        return (Long) redisTemplate.execute((RedisCallback) connection -> {
            LOGGER.info("??????Redis?????????");
            return connection.dbSize();
        });
    }

    @Override
    public String ping() throws DataAccessException {
        return (String) redisTemplate.execute((RedisCallback) connection -> connection.ping());
    }

    /**
     * ???????????????
     *
     * @param obj ??????
     * @return ???????????????
     */
    @SuppressWarnings("unchecked")
    private <T> byte[] serialize(T obj) {
        try {
            final RedisSerializer<T> redisSerializer = (RedisSerializer<T>) redisTemplate.getValueSerializer();
            return redisSerializer.serialize(obj);
        } catch (Exception e) {
            LOGGER.error("?????????????????????:{}", e);
            return new byte[0];
        }
    }

    /**
     * ??????????????????
     *
     * @param bytes ????????????
     * @return ??????
     */
    @SuppressWarnings("unchecked")
    private <T> T deserialize(byte[] bytes) {
        try {
            final RedisSerializer<T> redisSerializer = (RedisSerializer<T>)
                    redisTemplate.getValueSerializer();
            return redisSerializer.deserialize(bytes);
        } catch (Exception e) {
            LOGGER.error("????????????????????????:{}", e);
            return null;
        }
    }
}
