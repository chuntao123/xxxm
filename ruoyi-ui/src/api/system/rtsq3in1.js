import request from '@/utils/request'
import axios from 'axios';
import qs from 'qs'
// 查询rtsq列表
export function listRtsq3in1(query) {
  return request({
    url: '/system/rtsq3in1/list',
    method: 'get',
    params: query
  })
}

// 查询rtsq菜单列表
export function menulistRtsq3in1(query) {
  return request({
    url: '/system/rtsq3in1/menulist',
    method: 'get',
    params: query
  })
}

// 查询报表数据列表
export function bblistRtsq3in1(query) {
  return request({
    url: '/system/rtsq3in1/bblist',
    method: 'get',
    params: query
  })
}

// 查询rtsq特殊值列表
export function speciallistRtsq3in1(query) {
  return request({
    url: '/system/rtsq3in1/speciallist?'+query,
    method: 'get',
  })

}

// 查询rtsq详细
export function getRtsq3in1(senid) {
  return request({
    url: '/system/rtsq3in1/' + senid,
    method: 'get'
  })
}

// 新增rtsq
export function addRtsq3in1(data) {
  return request({
    url: '/system/rtsq3in1',
    method: 'post',
    data: data
  })
}

// 修改rtsq
export function updateRtsq3in1(data) {
  return request({
    url: '/system/rtsq3in1',
    method: 'put',
    data: data
  })
}

// 删除rtsq
export function delRtsq3in1(senid) {
  return request({
    url: '/system/rtsq3in1/' + senid,
    method: 'delete'
  })
}
