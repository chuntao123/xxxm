import request from '@/utils/request'

// 查询二类标记列表
export function listSwz(query) {
  return request({
    url: '/system/swz/list',
    method: 'get',
    params: query
  })
}

// 查询三类标记列表
export function threelist(query) {
  return request({
    url: '/system/swz/threelist',
    method: 'get',
    params: query
  })
}

// 查询二类标记详细
export function getSwz(stid) {
  return request({
    url: '/system/swz/' + stid,
    method: 'get'
  })
}

// 新增二类标记
export function addSwz(data) {
  return request({
    url: '/system/swz',
    method: 'post',
    data: data
  })
}

// 修改二类标记
export function updateSwz(data) {
  return request({
    url: '/system/swz',
    method: 'put',
    data: data
  })
}

// 删除二类标记
export function delSwz(stid) {
  return request({
    url: '/system/swz/' + stid,
    method: 'delete'
  })
}
