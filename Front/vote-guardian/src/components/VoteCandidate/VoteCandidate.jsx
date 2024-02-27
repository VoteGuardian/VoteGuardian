import { useState } from 'react';
import { useRouter } from "next/navigation";
import { FaRegImage } from "react-icons/fa6";
import { ImCross } from "react-icons/im";
import Button from '../Common/Button/Button';
import './VoteCandidate.scss'
import Alert from '../Common/Alert/Alert';


export default function VoteCandidate() {
    const router = useRouter();
    const [tagList, setTagList] = useState([]);
    const [name, setName] = useState('');
    const [detail, setDetail] = useState('');
    const [alert, setAlert] = useState(false);
    const [alertText, setAlertText] = useState('');

    //후보 이름
    function handleNameChange(e) {
        setName(e.target.value);
        //이름은 50글자 제한
        if(name.length > 50) {
            document.getElementsByClassName("name-error")[0].classList.add('show');
        }
        else document.getElementsByClassName("name-error")[0].classList.remove('show');
    }
    //상세 정보
    function handleDetailChange(e) {
        setDetail(e.target.value);
        if(detail.length > 1100) {
            document.getElementsByClassName("detail-error")[0].classList.add('show');
        }
        else document.getElementsByClassName("detail-error")[0].classList.add('show');
    }
    //태그 추가
    function handleTagAdd(e) {
        if(e.code === 'Enter' && e.nativeEvent.isComposing === false) {
            //태그 개수는 5개로 제한
            if(tagList.length < 5) {
                const tag = e.target.value;
                tagList.push(tag)
                setTagList([...tagList])
                //작성한 태그 내용 초기화
                e.target.value = ''
            }
            else {
                setAlertText('태그는 5개 이상 작성할 수 없습니다');
                handleAlert();
            }
        }
    }
    //태그 삭제
    function handleTagRemove(e) {
        //현재 삭제하려는 태그
        const tagText = e.target.parentNode.previousSibling.innerText;
        for(let i = 0; i < tagList.length; i++) {
            //태그 목록에서 일치하는 문자를 찾아 삭제
            if(`#${tagList[i]}` === tagText) {
                tagList.splice(i, 1);
            }
        }
        setTagList([...tagList])
    }
    //알람창
    function handleAlert() {
        //나타났다가 2초 뒤 쯤 사라지게 함
        setAlert(true)
        setTimeout(() => {
            setAlert(false)
        }, 2000)
    }
    //이전 버튼
    function handlePrev() {
        //후보 정보 입력 페이지로 이동
        router.push('/VoteCreate/Information')
    }
    //등록 버튼
    function handleRegister() {
        //후보 이름과 상세 정보는 필수이며 조건에 맞는지 확인
        if(name.length > 50 || detail.length > 1100) {
            setAlertText('후보 이름이나 후보 상세 정보를 작성 조건에 맞게 작성해주세요');
            handleAlert();
        }
        else if(name.length === 0) {
            setAlertText('후보 이름은 필수 작성 요소입니다');
            handleAlert();
        }
        else if(detail.length === 0) {
            setAlertText('상세 정보는 필수 작성 요소입니다');
            handleAlert();
        }
        else {
            //투표자 등록 페이지로 이동
            //router.push('')
        }
    }
    return(
        <>
            <div className="candidate-content-flex">
                {alert &&
                    <Alert type='negative' text={alertText}/>
                }
                <div>
                    <p className='register-title'>후보 등록</p>
                    <div className='candidate-info-flex'>
                        <div className='candidate-image-wrap'>
                            <FaRegImage size={100}/>
                        </div>
                        <div className='name-wrap'>
                            <p className='name-title'>후보 이름</p>
                            <input onInput={handleNameChange} type="text" placeholder='후보 이름을 작성해주세요' />
                            <p className="name-error">후보 이름은 50글자 이내로 작성해주세요</p>
                        </div>
                    </div>
                    <div className='tag-limit'>
                        <p>* 5개 제한</p>
                    </div>
                    <div className='tag-wrap'>
                        <p className='name-title'>태그</p>
                        <input onKeyDown={handleTagAdd} type='text' placeholder='Enter' />
                    </div>
                    <div className='tag-list-flex'>
                        {tagList && tagList.map((tag) => 
                        <>
                            <p>#{tag}</p>
                            <ImCross onClick={handleTagRemove}/>
                        </>
                        )}
                    </div>
                    <div className='detail-wrap'>
                        <p className='name-title'>상세 정보</p>
                        <textarea onInput={handleDetailChange} name="content" cols="50" rows="22" placeholder="후보에 대한 설명을 작성해주세요"/>
                        <p className="detail-error">후보자에 대한 상세 정보는 1100글자 이내로 작성해주세요</p>
                    </div>
                    <div className="button-group-flex">
                        <div onClick={handlePrev}>
                            <Button buttonType='select negative' text='이전'></Button>
                        </div>
                        <div onClick={handleRegister}>
                            <Button buttonType='select positive' text='등록'></Button>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}