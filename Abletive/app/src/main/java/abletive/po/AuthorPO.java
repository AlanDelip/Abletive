package abletive.po;

/**
 * 作者数据
 */
public class AuthorPO {
    int id;
    String slug;
    String name;
    String description;
    String url;
    String avatar;
    String membership;

    public AuthorPO(int id, String slug, String name, String description, String url, String avatar, String membership) {
        this.id = id;
        this.slug = slug;
        this.name = name;
        this.description = description;
        this.url = url;
        this.avatar = avatar;
        this.membership = membership;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMembership() {
        return membership;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }
}
