import request from '@/utils/request'

// 查询VIEW列表
export function listAll(query) {
  return request({
    url: '/system/all/list',
    method: 'get',
    params: query
  })
}

// 查询VIEW详细
export function getmap() {
  return request({
    url: '/system/all',
    method: 'get'
  })
}

// 新增VIEW
export function addAll(data) {
  return request({
    url: '/system/all',
    method: 'post',
    data: data
  })
}

// 修改VIEW
export function updateAll(data) {
  return request({
    url: '/system/all',
    method: 'put',
    data: data
  })
}

// 删除VIEW
export function delAll(summary) {
  return request({
    url: '/system/all/' + summary,
    method: 'delete'
  })
}
