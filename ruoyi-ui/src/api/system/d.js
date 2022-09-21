import request from '@/utils/request'
import axios from 'axios'
import { getToken } from '@/utils/auth'

// 查询二类数据列表
export function listD(query) {
  return request({
    url: '/system/d/list',
    method: 'get',
    params: query
  })
}

export function listrain(query) {
  return request({
    url: '/system/raindata/list',
    method: 'get',
    params: query
  })
}
export function alllistD(query) {
  return request({
    url: '/system/d/zdalllist',
    method: 'get',
    params: query
  })
}

// 查询站点数据列表
export function zdlistD(query) {
  return request({
    url: '/system/d/zdlist',
    method: 'get',
    params: query
  })
}

// 查询站点数据列表数量
export function forecasttotal(query) {
  return request({
    url: '/system/page/total',
    method: 'get',
    params: query
  })
}

// 查询预测数据列表
export function forecast(query) {
  let baseUrl = "http://192.168.1.2:8089";
  var r1 = axios({
    method:"get",
    url:baseUrl+'/system/d/zdlist',
    params: query,
    headers:{
      'Authorization' : 'Bearer ' + getToken()
    }
  });
  var r2 = axios({
    method:"get",
    url:baseUrl+'/system/d/zd6hlist',
    params: query,
    headers:{
      'Authorization' : 'Bearer ' + getToken()
    }
  });
  var r3 = axios({
    method:"get",
    url:baseUrl+'/system/d/zd12hlist',
    params: query,
    headers:{
      'Authorization' : 'Bearer ' + getToken()
    }
  });
  return axios.all([r1, r2, r3]);

}

// 查询6H预测数据列表
export function zd6hlistD(query) {
  return request({
    url: '/system/d/zd6hlist',
    method: 'get',
    params: query
  })
}

// 查询12h预测数据列表
export function zd12hlistD(query) {
  return request({
    url: '/system/d/zd12hlist',
    method: 'get',
    params: query
  })
}

// 查询二类数据详细
export function getD(wdid) {
  return request({
    url: '/system/d/' + wdid,
    method: 'get'
  })
}

// 新增二类数据
export function addD(data) {
  return request({
    url: '/system/d',
    method: 'post',
    data: data
  })
}

// 修改二类数据
export function updateD(data) {
  return request({
    url: '/system/d',
    method: 'put',
    data: data
  })
}

// 删除二类数据
export function delD(wdid) {
  return request({
    url: '/system/d/' + wdid,
    method: 'delete'
  })
}
