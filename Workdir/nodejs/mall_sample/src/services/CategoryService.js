import axios from 'axios';

const API_URL = 'http://localhost:8080/api/categories';

class CategoryService {
  // 모든 카테고리 가져오기
  getAllCategories() {
    return axios.get(API_URL);
  }

  // 활성화된 카테고리만 가져오기
  getActiveCategories() {
    return axios.get(`${API_URL}/active`);
  }

  // 비활성화된 카테고리만 가져오기
  getInactiveCategories() {
    return axios.get(`${API_URL}/inactive`);
  }

  // 계층별로 정렬된 모든 카테고리 가져오기
  getHierarchicalCategories() {
    return axios.get(`${API_URL}/hierarchical`);
  }

  // 계층 구조화된 카테고리 맵 가져오기
  getCategoryHierarchyMap() {
    return axios.get(`${API_URL}/hierarchy-map`);
  }
  
  // 활성화된 카테고리만으로 계층 구조화된 맵 가져오기
  getActiveCategoryHierarchyMap() {
    return axios.get(`${API_URL}/hierarchy-map/active`);
  }
  
  // 대분류(루트) 카테고리만 가져오기
  getRootCategories() {
    return axios.get(`${API_URL}/roots`);
  }
  
  // 활성화된 대분류 카테고리만 가져오기
  getActiveRootCategories() {
    return axios.get(`${API_URL}/roots/active`);
  }
  
  // 특정 depth의 카테고리 가져오기
  getCategoriesByDepth(depth) {
    return axios.get(`${API_URL}/depth/${depth}`);
  }
  
  // 특정 depth의 활성화된 카테고리만 가져오기
  getActiveCategoriesByDepth(depth) {
    return axios.get(`${API_URL}/depth/${depth}/active`);
  }

  // ID로 카테고리 가져오기
  getCategoryById(id) {
    return axios.get(`${API_URL}/${id}`);
  }

  // 상위 카테고리의 하위 카테고리 가져오기
  getSubcategories(parentId) {
    return axios.get(`${API_URL}/parent/${parentId}`);
  }
  
  // 상위 카테고리의 활성화된 하위 카테고리만 가져오기
  getActiveSubcategories(parentId) {
    return axios.get(`${API_URL}/parent/${parentId}/active`);
  }

  // 카테고리 생성
  createCategory(category) {
    return axios.post(API_URL, category);
  }

  // 카테고리 수정
  updateCategory(id, category) {
    return axios.put(`${API_URL}/${id}`, category);
  }

  // 카테고리 삭제 (하위 카테고리도 함께 삭제)
  deleteCategory(id) {
    return axios.delete(`${API_URL}/${id}`);
  }

  // 카테고리 이름으로 검색
  searchCategories(name) {
    return axios.get(`${API_URL}/search?name=${encodeURIComponent(name)}`);
  }

  // 카테고리 활성화/비활성화
  updateCategoryActiveStatus(id, active) {
    return axios.patch(`${API_URL}/${id}/active`, { active });
  }

  // 카테고리 이미지 ID 업데이트
  updateCategoryImage(id, imageId) {
    return axios.patch(`${API_URL}/${id}/image`, { imageId });
  }
}

export default new CategoryService(); 