import request from '@/utils/request'

// 查询论文信息列表
export function listThesisInfo(query) {
  return request({
    url: '/manage/thesisInfo/list',
    method: 'get',
    params: query
  })
}

// 查询论文信息详细
export function getThesisInfo(id) {
  return request({
    url: '/manage/thesisInfo/' + id,
    method: 'get'
  })
}

// 新增论文信息
export function addThesisInfo(data) {
  return request({
    url: '/manage/thesisInfo',
    method: 'post',
    data: data
  })
}

// 修改论文信息
export function updateThesisInfo(data) {
  return request({
    url: '/manage/thesisInfo',
    method: 'put',
    data: data
  })
}

// 删除论文信息
export function delThesisInfo(编号) {
  return request({
    url: '/manage/thesisInfo/' + 编号,
    method: 'delete'
  })
}
