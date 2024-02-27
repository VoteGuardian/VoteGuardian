import { useState } from 'react';
import { useRouter } from "next/navigation";
import { FaRegImage } from "react-icons/fa6";
import { ImCross } from "react-icons/im";
import Button from '../Common/Button/Button';
import './VoteCandidate.scss'


export default function VoteCandidate() {
    const [tagList, setTagList] = useState([]);
    const router = useRouter();

    //후보 이름
    let name = '';
    function handleNameChange(e) {
        name = e.target.value;
        //제목은 50글자 제한
        if(name.length > 50) {
            document.getElementsByClassName("name-error")[0].classList.add('show');
        }
        else document.getElementsByClassName("name-error")[0].classList.remove('show');
    }
    //태그 추가
    function handleTagAdd(e) {
        if(e.code === 'Enter' && e.nativeEvent.isComposing === false) {
            if(tagList.length < 5) {
                const tag = e.target.value;
                tagList.push(tag)
                console.log(tagList)
                setTagList([...tagList])
                e.target.value = ''
            }
            else {
                console.log('5개 이상은 안 됨')
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
    //상세 정보
    let detailText = '';
    function handleDetailChange(e) {
        detailText = e.target.value;
        console.log(detailText.length)
        if(detailText.length > 1100) {
            document.getElementsByClassName("detail-error")[0].classList.add('show');
        }
        else document.getElementsByClassName("detail-error")[0].classList.add('show');
    }
    //이전 버튼
    function handlePrev() {
        router.push('/VoteCreate/Information')
    }
    //등록 버튼
    function handleRegister() {
        console.log('등록')
        //등록 버튼을 누르면..
        //사진을 제외한 모든 요소는 필수
        if(name.length > 50) {
            //
        }
    }

    return(
        <>
            <div className="candidate-content-flex">
                <div>
                    <p className='register-title'>후보 등록</p>
                    <div className='candidate-info-flex'>
                        <div className='candidate-image-wrap'>
                            <FaRegImage size={100}/>
                        </div>
                        <div className='name-wrap'>
                            <p className='name-title'>후보 이름</p>
                            <input onInput={handleNameChange} type="text" />
                            <p className="name-error">후보 이름은 50글자 이내로 작성해주세요</p>
                        </div>
                    </div>
                    <div className='tag-limit'>
                        <p>* 5개 제한</p>
                    </div>
                    <div className='tag-wrap'>
                        <p className='name-title'>태그</p>
                        <input onKeyDown={handleTagAdd} type='text'/>
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
                        <textarea onInput={handleDetailChange} name="content" cols="50" rows="22" placeholder="투표에 대해서 설명할 내용을 작성하세요"/>
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