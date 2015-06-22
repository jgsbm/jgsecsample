package jgs.bluemix.sample.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 全てのエンティティのベースクラスです.
 * 全エンティティ共通のフィールドを保持します。
 *
 * @author ryozo
 */
@Data
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 5776265570323589855L;
    private Long id;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Long version;
}
