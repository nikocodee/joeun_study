import React, { useState, useEffect } from 'react';
import { Container, Row, Col, Card, Table, Button, Form, Modal, Alert, Badge, Image } from 'react-bootstrap';
import CategoryService from '../services/CategoryService';
import './CategoryManager.css';

function CategoryManager() {
  const [categories, setCategories] = useState([]);
  const [rootCategories, setRootCategories] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState(null);
  const [showModal, setShowModal] = useState(false);
  const [showImageModal, setShowImageModal] = useState(false);
  const [modalMode, setModalMode] = useState('create'); // 'create' 또는 'edit'
  const [formData, setFormData] = useState({
    name: '',
    parentId: null,
    depth: 0,
    active: true,
    imageId: ''
  });
  const [imageFile, setImageFile] = useState(null);
  const [imagePreview, setImagePreview] = useState(null);
  const [keyword, setKeyword] = useState('');
  const [alert, setAlert] = useState({ show: false, variant: '', message: '' });
  const [viewMode, setViewMode] = useState('all'); // 'all', 'hierarchical', 'roots', 'active'

  // 모든 카테고리 조회
  const fetchAllCategories = async () => {
    try {
      const response = await CategoryService.getAllCategories();
      setCategories(response.data);
    } catch (error) {
      console.error('카테고리 조회 오류:', error);
      showAlertMessage('danger', '카테고리 목록을 불러오는데 실패했습니다.');
    }
  };

  // 대분류 카테고리만 조회
  const fetchRootCategories = async () => {
    try {
      const response = await CategoryService.getRootCategories();
      setRootCategories(response.data);
    } catch (error) {
      console.error('대분류 카테고리 조회 오류:', error);
      showAlertMessage('danger', '대분류 카테고리 목록을 불러오는데 실패했습니다.');
    }
  };

  // 계층별 정렬된 카테고리 조회
  const fetchHierarchicalCategories = async () => {
    try {
      const response = await CategoryService.getHierarchicalCategories();
      setCategories(response.data);
    } catch (error) {
      console.error('계층별 카테고리 조회 오류:', error);
      showAlertMessage('danger', '계층별 카테고리 목록을 불러오는데 실패했습니다.');
    }
  };

  // 활성화된 카테고리만 조회
  const fetchActiveCategories = async () => {
    try {
      const response = await CategoryService.getActiveCategories();
      setCategories(response.data);
    } catch (error) {
      console.error('활성화된 카테고리 조회 오류:', error);
      showAlertMessage('danger', '활성화된 카테고리 목록을 불러오는데 실패했습니다.');
    }
  };

  // 보기 모드에 따라 데이터 조회
  const fetchCategoriesByViewMode = () => {
    if (viewMode === 'all') {
      fetchAllCategories();
    } else if (viewMode === 'hierarchical') {
      fetchHierarchicalCategories();
    } else if (viewMode === 'roots') {
      fetchRootCategories();
      setCategories([]); // 대분류만 보여줄 때는 일반 카테고리 목록은 비움
    } else if (viewMode === 'active') {
      fetchActiveCategories();
    }
  };

  // 컴포넌트 마운트 시 카테고리 조회
  useEffect(() => {
    fetchAllCategories();
    fetchRootCategories();
  }, []);

  // 보기 모드 변경 시 데이터 다시 조회
  useEffect(() => {
    fetchCategoriesByViewMode();
  }, [viewMode]);

  // 알림 메시지 표시
  const showAlertMessage = (variant, message) => {
    setAlert({ show: true, variant, message });
    setTimeout(() => {
      setAlert({ show: false, variant: '', message: '' });
    }, 3000);
  };

  // 입력 필드 변경 처리
  const handleInputChange = (e) => {
    const { name, value, type, checked } = e.target;
    
    if (name === 'parentId') {
      // parentId 변경 시 depth도 자동으로 설정
      const depth = value ? 1 : 0;
      setFormData({
        ...formData,
        [name]: value,
        depth
      });
    } else if (type === 'checkbox') {
      setFormData({
        ...formData,
        [name]: checked
      });
    } else {
      setFormData({
        ...formData,
        [name]: value
      });
    }
  };

  // 이미지 파일 선택 시 처리
  const handleImageChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      setImageFile(file);
      
      // 이미지 미리보기 생성
      const reader = new FileReader();
      reader.onloadend = () => {
        setImagePreview(reader.result);
      };
      reader.readAsDataURL(file);
    }
  };

  // 이미지 업로드 처리
  const handleImageUpload = async () => {
    if (!imageFile) {
      showAlertMessage('warning', '업로드할 이미지 파일을 선택해주세요.');
      return;
    }

    try {
      // FormData 객체 생성
      const formData = new FormData();
      formData.append('file', imageFile);

      // 이미지 업로드 API 호출
      const response = await fetch('http://localhost:8080/api/images/upload', {
        method: 'POST',
        body: formData
      });

      const data = await response.json();
      if (response.ok && data.fileId) {
        // 업로드 성공, 이미지 ID 저장
        setFormData(prev => ({
          ...prev,
          imageId: data.fileId
        }));
        showAlertMessage('success', '이미지가 성공적으로 업로드되었습니다.');
        setShowImageModal(false);
      } else {
        throw new Error(data.message || '이미지 업로드에 실패했습니다.');
      }
    } catch (error) {
      console.error('이미지 업로드 오류:', error);
      showAlertMessage('danger', '이미지 업로드에 실패했습니다.');
    }
  };

  // 모달 열기 - 새 카테고리 생성
  const handleShowCreateModal = () => {
    setFormData({
      name: '',
      parentId: '',
      depth: 0,
      active: true,
      imageId: ''
    });
    setImagePreview(null);
    setModalMode('create');
    setShowModal(true);
  };

  // 모달 열기 - 카테고리 수정
  const handleShowEditModal = (category) => {
    setSelectedCategory(category);
    setFormData({
      name: category.name,
      parentId: category.parentId || '',
      depth: category.depth,
      active: category.active,
      imageId: category.imageId || ''
    });
    setImagePreview(category.imageId ? `http://localhost:8080/api/images/${category.imageId}` : null);
    setModalMode('edit');
    setShowModal(true);
  };

  // 모달 닫기
  const handleCloseModal = () => {
    setShowModal(false);
    setSelectedCategory(null);
    setImagePreview(null);
  };

  // 이미지 업로드 모달 열기/닫기
  const handleShowImageModal = () => setShowImageModal(true);
  const handleCloseImageModal = () => setShowImageModal(false);

  // 카테고리 저장 (생성 또는 수정)
  const handleSaveCategory = async () => {
    try {
      if (modalMode === 'create') {
        // 새 카테고리 생성
        await CategoryService.createCategory(formData);
        showAlertMessage('success', '카테고리가 성공적으로 생성되었습니다.');
      } else {
        // 카테고리 수정
        await CategoryService.updateCategory(selectedCategory.id, formData);
        showAlertMessage('success', '카테고리가 성공적으로 수정되었습니다.');
      }
      
      handleCloseModal();
      fetchCategoriesByViewMode();
      fetchRootCategories(); // 부모 카테고리 목록 업데이트를 위해
    } catch (error) {
      console.error('카테고리 저장 오류:', error);
      showAlertMessage('danger', '카테고리 저장에 실패했습니다.');
    }
  };

  // 카테고리 삭제
  const handleDeleteCategory = async (id) => {
    if (window.confirm('이 카테고리를 삭제하시겠습니까?\n하위 카테고리도 모두 삭제됩니다.')) {
      try {
        await CategoryService.deleteCategory(id);
        showAlertMessage('success', '카테고리가 삭제되었습니다.');
        fetchCategoriesByViewMode();
        fetchRootCategories(); // 부모 카테고리 목록 업데이트를 위해
      } catch (error) {
        console.error('카테고리 삭제 오류:', error);
        showAlertMessage('danger', '카테고리 삭제에 실패했습니다.');
      }
    }
  };

  // 카테고리 활성화/비활성화 토글
  const handleToggleActive = async (id, currentActive) => {
    try {
      await CategoryService.updateCategoryActiveStatus(id, !currentActive);
      showAlertMessage('success', `카테고리가 ${!currentActive ? '활성화' : '비활성화'} 되었습니다.`);
      fetchCategoriesByViewMode();
    } catch (error) {
      console.error('카테고리 상태 변경 오류:', error);
      showAlertMessage('danger', '카테고리 상태 변경에 실패했습니다.');
    }
  };

  // 검색 처리
  const handleSearch = async () => {
    if (!keyword.trim()) {
      fetchCategoriesByViewMode();
      return;
    }
    
    try {
      const response = await CategoryService.searchCategories(keyword);
      setCategories(response.data);
    } catch (error) {
      console.error('카테고리 검색 오류:', error);
      showAlertMessage('danger', '카테고리 검색에 실패했습니다.');
    }
  };

  // 보기 모드 변경 처리
  const handleViewModeChange = (e) => {
    setViewMode(e.target.value);
  };

  // depth에 따른 카테고리 레벨 표시
  const renderCategoryLevelBadge = (depth) => {
    const variant = depth === 0 ? 'primary' : 'info';
    const label = depth === 0 ? '대분류' : '소분류';
    
    return <Badge bg={variant}>{label}</Badge>;
  };

  // 활성화 상태에 따른 배지 표시
  const renderActiveBadge = (active) => {
    const variant = active ? 'success' : 'secondary';
    const label = active ? '활성' : '비활성';
    
    return <Badge bg={variant}>{label}</Badge>;
  };

  return (
    <Container className="category-manager my-4">
      {alert.show && (
        <Alert variant={alert.variant} onClose={() => setAlert({ ...alert, show: false })} dismissible>
          {alert.message}
        </Alert>
      )}
      
      <Card>
        <Card.Header className="d-flex justify-content-between align-items-center">
          <h4>카테고리 관리</h4>
          <Button variant="primary" onClick={handleShowCreateModal}>
            새 카테고리 추가
          </Button>
        </Card.Header>
        
        <Card.Body>
          <Row className="mb-3">
            <Col md={6}>
              <Form.Group as={Row}>
                <Col>
                  <Form.Control
                    type="text"
                    placeholder="카테고리 이름 검색"
                    value={keyword}
                    onChange={(e) => setKeyword(e.target.value)}
                  />
                </Col>
                <Col xs="auto">
                  <Button variant="outline-secondary" onClick={handleSearch}>검색</Button>
                </Col>
              </Form.Group>
            </Col>
            <Col md={6}>
              <Form.Group as={Row} className="justify-content-end">
                <Col xs="auto">
                  <Form.Select 
                    value={viewMode}
                    onChange={handleViewModeChange}
                  >
                    <option value="all">모든 카테고리</option>
                    <option value="active">활성화된 카테고리</option>
                    <option value="hierarchical">계층별 카테고리</option>
                    <option value="roots">대분류만 보기</option>
                  </Form.Select>
                </Col>
              </Form.Group>
            </Col>
          </Row>
          
          {viewMode === 'roots' ? (
            // 대분류만 볼 때의 테이블
            <Table responsive striped hover>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>이름</th>
                  <th>이미지</th>
                  <th>구분</th>
                  <th>상태</th>
                  <th>액션</th>
                </tr>
              </thead>
              <tbody>
                {rootCategories.map((category) => (
                  <tr key={category.id}>
                    <td>{category.id}</td>
                    <td>{category.name}</td>
                    <td>
                      {category.imageId && (
                        <Image 
                          src={`http://localhost:8080/api/images/${category.imageId}`}
                          width={40}
                          height={40}
                          rounded
                          className="category-image"
                        />
                      )}
                    </td>
                    <td>{renderCategoryLevelBadge(category.depth)}</td>
                    <td>{renderActiveBadge(category.active)}</td>
                    <td>
                      <Button
                        variant="outline-primary"
                        size="sm"
                        onClick={() => handleShowEditModal(category)}
                        className="me-2"
                      >
                        수정
                      </Button>
                      <Button
                        variant={category.active ? 'outline-warning' : 'outline-success'}
                        size="sm"
                        onClick={() => handleToggleActive(category.id, category.active)}
                        className="me-2"
                      >
                        {category.active ? '비활성화' : '활성화'}
                      </Button>
                      <Button
                        variant="outline-danger"
                        size="sm"
                        onClick={() => handleDeleteCategory(category.id)}
                      >
                        삭제
                      </Button>
                    </td>
                  </tr>
                ))}
                {rootCategories.length === 0 && (
                  <tr>
                    <td colSpan="6" className="text-center">
                      등록된 대분류 카테고리가 없습니다.
                    </td>
                  </tr>
                )}
              </tbody>
            </Table>
          ) : (
            // 일반/계층형 카테고리 테이블
            <Table responsive striped hover>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>이름</th>
                  <th>이미지</th>
                  <th>구분</th>
                  <th>상태</th>
                  <th>상위 카테고리</th>
                  <th>액션</th>
                </tr>
              </thead>
              <tbody>
                {categories.map((category) => (
                  <tr key={category.id}>
                    <td>{category.id}</td>
                    <td>{category.name}</td>
                    <td>
                      {category.imageId && (
                        <Image 
                          src={`http://localhost:8080/api/images/${category.imageId}`}
                          width={40}
                          height={40}
                          rounded
                          className="category-image"
                        />
                      )}
                    </td>
                    <td>{renderCategoryLevelBadge(category.depth)}</td>
                    <td>{renderActiveBadge(category.active)}</td>
                    <td>{category.parentId || '-'}</td>
                    <td>
                      <Button
                        variant="outline-primary"
                        size="sm"
                        onClick={() => handleShowEditModal(category)}
                        className="me-2"
                      >
                        수정
                      </Button>
                      <Button
                        variant={category.active ? 'outline-warning' : 'outline-success'}
                        size="sm"
                        onClick={() => handleToggleActive(category.id, category.active)}
                        className="me-2"
                      >
                        {category.active ? '비활성화' : '활성화'}
                      </Button>
                      <Button
                        variant="outline-danger"
                        size="sm"
                        onClick={() => handleDeleteCategory(category.id)}
                      >
                        삭제
                      </Button>
                    </td>
                  </tr>
                ))}
                {categories.length === 0 && (
                  <tr>
                    <td colSpan="7" className="text-center">
                      등록된 카테고리가 없습니다.
                    </td>
                  </tr>
                )}
              </tbody>
            </Table>
          )}
        </Card.Body>
      </Card>
      
      {/* 카테고리 모달 */}
      <Modal show={showModal} onHide={handleCloseModal}>
        <Modal.Header closeButton>
          <Modal.Title>
            {modalMode === 'create' ? '새 카테고리 추가' : '카테고리 수정'}
          </Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Group className="mb-3">
              <Form.Label>카테고리 이름</Form.Label>
              <Form.Control
                type="text"
                name="name"
                value={formData.name}
                onChange={handleInputChange}
                required
              />
            </Form.Group>
            
            <Form.Group className="mb-3">
              <Form.Label>카테고리 구분</Form.Label>
              <div className="mb-2">
                {renderCategoryLevelBadge(formData.depth)}
                <small className="text-muted ms-2">
                  {formData.depth === 0 
                    ? '상위 카테고리가 없는 대분류 카테고리입니다' 
                    : '상위 카테고리를 가진 소분류 카테고리입니다'}
                </small>
              </div>
            </Form.Group>
            
            <Form.Group className="mb-3">
              <Form.Label>상위 카테고리</Form.Label>
              <Form.Select
                name="parentId"
                value={formData.parentId}
                onChange={handleInputChange}
              >
                <option value="">없음 (대분류 카테고리)</option>
                {rootCategories
                  .filter(cat => selectedCategory ? cat.id !== selectedCategory.id : true)
                  .map(category => (
                    <option key={category.id} value={category.id}>
                      {category.name}
                    </option>
                  ))}
              </Form.Select>
              <Form.Text className="text-muted">
                상위 카테고리를 선택하면 자동으로 소분류로 설정됩니다.
              </Form.Text>
            </Form.Group>
            
            <Form.Group className="mb-3">
              <Form.Label>카테고리 이미지</Form.Label>
              <div className="d-flex align-items-center mb-2">
                {imagePreview && (
                  <div className="me-3">
                    <Image 
                      src={imagePreview} 
                      width={80} 
                      height={80} 
                      rounded 
                      className="category-image-preview"
                    />
                  </div>
                )}
                <Button variant="outline-primary" onClick={handleShowImageModal}>
                  이미지 {formData.imageId ? '변경' : '추가'}
                </Button>
                {formData.imageId && (
                  <Button 
                    variant="outline-danger" 
                    className="ms-2"
                    onClick={() => {
                      setFormData({...formData, imageId: ''});
                      setImagePreview(null);
                    }}
                  >
                    이미지 제거
                  </Button>
                )}
              </div>
              <Form.Text className="text-muted">
                카테고리를 대표하는 이미지를 설정할 수 있습니다.
              </Form.Text>
            </Form.Group>
            
            <Form.Group className="mb-3">
              <Form.Check
                type="checkbox"
                label="활성화"
                name="active"
                checked={formData.active}
                onChange={handleInputChange}
              />
              <Form.Text className="text-muted">
                비활성화된 카테고리는 사용자에게 표시되지 않습니다.
              </Form.Text>
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleCloseModal}>
            취소
          </Button>
          <Button variant="primary" onClick={handleSaveCategory}>
            저장
          </Button>
        </Modal.Footer>
      </Modal>
      
      {/* 이미지 업로드 모달 */}
      <Modal show={showImageModal} onHide={handleCloseImageModal}>
        <Modal.Header closeButton>
          <Modal.Title>카테고리 이미지 업로드</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Group className="mb-3">
              <Form.Label>이미지 파일 선택</Form.Label>
              <Form.Control
                type="file"
                accept="image/*"
                onChange={handleImageChange}
              />
              <Form.Text className="text-muted">
                JPG, PNG, GIF 형식의 이미지 파일을 선택해주세요.
              </Form.Text>
            </Form.Group>
            
            {imagePreview && (
              <div className="text-center my-3">
                <Image 
                  src={imagePreview} 
                  width={200} 
                  className="img-thumbnail"
                />
              </div>
            )}
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleCloseImageModal}>
            취소
          </Button>
          <Button 
            variant="primary" 
            onClick={handleImageUpload}
            disabled={!imageFile}
          >
            업로드
          </Button>
        </Modal.Footer>
      </Modal>
    </Container>
  );
}

export default CategoryManager; 