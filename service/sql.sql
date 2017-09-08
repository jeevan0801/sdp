CREATE TABLE T_SDP_SPINFO
(
  BASEURL   VARCHAR2(200),
  SPNAME    VARCHAR2(200),
  SPID      VARCHAR2(10),
  KEY       VARCHAR2(200),
  ACCOUNTID VARCHAR2(200)
);
comment on table t_sdp_spinfo IS '上游sp信息';
comment on column t_sdp_spinfo.BASEURL  is '接口基础地质';
comment on column t_sdp_spinfo.SPNAME  is '上游名称';
comment on column t_sdp_spinfo.SPID  is '上游编号';
comment on column t_sdp_spinfo.KEY is '上游给的Key,一般用于加密';
comment on column t_sdp_spinfo.ACCOUNTID is '上游给的账号';