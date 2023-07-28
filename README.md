## ElastiSearch的入门程序

对于ES新入门小伙伴尽量选中7.17版本不要选择8版本，因为ES8中默认开启安全认证的，为了安全性，不能使用root账号启动，需切换其他账号，或新建账号来启动。

启动无法访问
原因：ES8默认开启了 ssl 认证
解决：修改elasticsearch.yml配置文件：
xpack.security.http.ssl:enabled 设置成 false
xpack.security.enabled 设置成false

network.host: 0.0.0.0
#
# By default Elasticsearch listens for HTTP traffic on the first free port it
# finds starting at 9200. Set a specific HTTP port here:
#
http.port: 9200
xpack.security.enabled: false

xpack.security.enrollment.enabled: true

# Enable encryption for HTTP API client connections, such as Kibana, Logstash, and Agents
xpack.security.http.ssl:
  enabled: false
  keystore.path: certs/http.p12

# Enable encryption and mutual authentication between cluster nodes
xpack.security.transport.ssl:
  enabled: true
  verification_mode: certificate
  keystore.path: certs/transport.p12
  truststore.path: certs/transport.p12
# Create a new cluster with the current node only
# Additional nodes can still join the cluster later
cluster.initial_master_nodes: ["VM-4-12-centos"]
