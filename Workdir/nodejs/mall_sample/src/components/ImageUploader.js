import React, { useState } from 'react';
import { Form, Button, Alert, Image, Card } from 'react-bootstrap';
import axios from 'axios';
import './ImageUploader.css';

function ImageUploader({ onImageUploaded }) {
  const [file, setFile] = useState(null);
  const [previewUrl, setPreviewUrl] = useState('');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [uploadedImageId, setUploadedImageId] = useState('');
  const [uploadedImages, setUploadedImages] = useState([]);

  // 파일 선택 시 미리보기 생성
  const handleFileChange = (e) => {
    const selectedFile = e.target.files[0];
    if (selectedFile) {
      if (!selectedFile.type.startsWith('image/')) {
        setError('이미지 파일만 업로드 가능합니다.');
        setFile(null);
        setPreviewUrl('');
        return;
      }
      
      setFile(selectedFile);
      setError('');
      
      // 이미지 미리보기 생성
      const reader = new FileReader();
      reader.onloadend = () => {
        setPreviewUrl(reader.result);
      };
      reader.readAsDataURL(selectedFile);
    }
  };

  // 이미지 업로드 처리
  const handleUpload = async () => {
    if (!file) {
      setError('업로드할 이미지를 선택해주세요.');
      return;
    }

    setLoading(true);
    setError('');

    try {
      // FormData 객체 생성
      const formData = new FormData();
      formData.append('file', file);

      // 서버로 이미지 업로드 요청
      const response = await axios.post('http://localhost:8080/api/images/upload', 
                                        formData, 
                                        {
                                          headers: {
                                            'Content-Type': 'multipart/form-data'
                                          }
                                        }
                                       );

      // 업로드 성공 시
      if (response.data && response.data.fileId) {
        const imageId = response.data.fileId;
        setUploadedImageId(imageId);
        setUploadedImages([...uploadedImages, {
          id: imageId,
          name: file.name
        }]);
        setFile(null);
        setPreviewUrl('');
        
        // 부모 컴포넌트로 이미지 ID 전달 (있는 경우)
        if (onImageUploaded) {
          onImageUploaded(imageId);
        }
      }
    } catch (err) {
      setError('이미지 업로드 중 오류가 발생했습니다. 다시 시도해주세요.');
      console.error('Upload error:', err);
    } finally {
      setLoading(false);
    }
  };

  // 이미지 선택 처리
  const handleSelectImage = (imageId) => {
    if (onImageUploaded) {
      onImageUploaded(imageId);
    }
  };

  return (
    <div className="image-uploader-container">
      <Card className="upload-card">
        <Card.Header as="h5">이미지 업로드</Card.Header>
        <Card.Body>
          <Form>
            <Form.Group className="mb-3">
              <Form.Label>이미지 선택</Form.Label>
              <Form.Control
                type="file"
                accept="image/*"
                onChange={handleFileChange}
                disabled={loading}
              />
              <Form.Text className="text-muted">
                JPG, PNG, GIF 형식의 이미지만 업로드 가능합니다.
              </Form.Text>
            </Form.Group>
            
            {error && <Alert variant="danger">{error}</Alert>}
            
            {previewUrl && (
              <div className="preview-container mb-3">
                <p>이미지 미리보기:</p>
                <Image 
                  src={previewUrl} 
                  alt="미리보기" 
                  style={{ width: '100px', height: '100px', objectFit: 'cover' }} 
                  thumbnail 
                />
              </div>
            )}
            
            <Button
              variant="primary"
              onClick={handleUpload}
              disabled={!file || loading}
            >
              {loading ? '업로드 중...' : '업로드'}
            </Button>
          </Form>
        </Card.Body>
      </Card>
      
      {uploadedImages.length > 0 && (
        <Card className="mt-4">
          <Card.Header as="h5">업로드된 이미지</Card.Header>
          <Card.Body>
            <div className="uploaded-images">
              {uploadedImages.map((img) => (
                <div key={img.id} className="uploaded-image-item">
                  <Image
                    src={`http://localhost:8080/api/images/${img.id}`}
                    alt={img.name}
                    style={{ width: '100px', height: '100px', objectFit: 'cover' }}
                    thumbnail
                    onClick={() => handleSelectImage(img.id)}
                    className={onImageUploaded ? "selectable-image" : ""}
                  />
                  <p className="image-name">{img.name}</p>
                  <code className="image-id">{img.id}</code>
                  {onImageUploaded && (
                    <Button 
                      variant="outline-primary" 
                      size="sm" 
                      className="mt-1"
                      onClick={() => handleSelectImage(img.id)}
                    >
                      선택
                    </Button>
                  )}
                </div>
              ))}
            </div>
          </Card.Body>
        </Card>
      )}
    </div>
  );
}

export default ImageUploader; 